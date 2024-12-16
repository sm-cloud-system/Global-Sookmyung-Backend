package com.sookmyung.global.external.jwt.service;

import static com.sookmyung.global.common.code.fail.AuthExceptionCode.INVALID_AUTH_REQUEST;
import static com.sookmyung.global.common.code.fail.AuthExceptionCode.INVALID_TOKEN;
import static com.sookmyung.global.common.code.fail.AuthExceptionCode.INVALID_TOKEN_HEADER;

import java.util.*;

import jakarta.servlet.http.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

import com.sookmyung.global.common.enums.*;
import com.sookmyung.global.common.exception.*;
import com.sookmyung.global.domain.auth.dto.request.*;
import com.sookmyung.global.domain.auth.dto.response.*;
import com.sookmyung.global.external.jwt.provider.*;
import com.sookmyung.global.external.jwt.validator.*;
import com.sookmyung.global.external.redis.dto.*;
import com.sookmyung.global.external.redis.repository.*;

import lombok.*;

@Service
@RequiredArgsConstructor
@Getter
public class JwtServiceImpl implements JwtService {
  private static final String BEARER = "Bearer ";
  private static final String ID_CLAIM = "memberId";
  private static final String ROLE_CLAIM = "roles";

  @Value("${jwt.access.header}")
  private String accessHeader;

  @Value("${jwt.refresh.header}")
  private String refreshHeader;

  private final RedisTokenRepository redisTokenRepository;

  private final JwtTokenProvider jwtTokenProvider;
  private final JwtTokenValidator jwtTokenValidator;

  @Override
  public IssueTokenResponse issueToken(final String memberId, final List<String> roles) {
    String accessToken = jwtTokenProvider.createAccessTokenForMember(memberId, roles);

    boolean isServiceMember =
        roles.stream()
            .anyMatch(
                role ->
                    role.equals(Role.SOOKMYUNG_STUDENT.name())
                        || role.equals(Role.INTERNATIONAL_STUDENT.name()));

    if (isServiceMember) {
      String refreshToken = jwtTokenProvider.createRefreshToken();
      updateRefreshToken(memberId, refreshToken);
      return IssueTokenResponse.of(accessToken, refreshToken);
    }

    throw new AuthException(INVALID_AUTH_REQUEST);
  }

  @Override
  public IssueTokenResponse reissueToken(final IssueTokenRequest request) {
    String accessToken = request.accessToken();
    String refreshToken = request.refreshToken();

    jwtTokenValidator.validateToken(refreshToken);

    String memberId = getMemberIdFromAccessToken(accessToken);
    RefreshTokenDto foundRefreshToken = getRefreshTokenForMemberId(memberId);

    validateRefreshTokenMatch(refreshToken, foundRefreshToken);

    List<String> roles = getRolesFromAccessToken(accessToken);

    String newAccessToken = jwtTokenProvider.createAccessTokenForMember(memberId, roles);
    String newRefreshToken = jwtTokenProvider.createRefreshToken();

    updateRefreshToken(memberId, newRefreshToken);

    return IssueTokenResponse.of(newAccessToken, newRefreshToken);
  }

  @Override
  public String getMemberIdFromAccessToken(final String accessToken) {
    return jwtTokenValidator.getClaim(accessToken, ID_CLAIM).asString();
  }

  private RefreshTokenDto getRefreshTokenForMemberId(final String memberId) {
    return redisTokenRepository.findByMemberIdOrElseThrowException(memberId);
  }

  private void validateRefreshTokenMatch(
      final String refreshToken, final RefreshTokenDto foundRefreshToken) {
    if (!refreshToken.equals(foundRefreshToken.getRefreshToken())) {
      throw new AuthException(INVALID_TOKEN);
    }
  }

  @Override
  public List<String> getRolesFromAccessToken(final String accessToken) {
    return jwtTokenValidator.getClaim(accessToken, ROLE_CLAIM).asList(String.class);
  }

  private void updateRefreshToken(final String memberId, final String newRefreshToken) {
    RefreshTokenDto refreshTokenDTO = redisTokenRepository.findByMemberId(memberId).orElse(null);

    if (refreshTokenDTO != null) {
      refreshTokenDTO.updateRefreshToken(newRefreshToken);
      redisTokenRepository.save(refreshTokenDTO);
      return;
    }

    redisTokenRepository.save(
        RefreshTokenDto.builder()
            .memberId(String.valueOf(memberId))
            .refreshToken(newRefreshToken)
            .build());
  }

  @Override
  public String extractAccessToken(final HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader(accessHeader))
        .filter(accessToken -> accessToken.startsWith(BEARER))
        .map(accessToken -> accessToken.replace(BEARER, ""))
        .orElseThrow(() -> new AuthException(INVALID_TOKEN_HEADER));
  }

  @Override
  public String extractRefreshToken(final HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader(refreshHeader))
        .filter(refreshToken -> refreshToken.startsWith(BEARER))
        .map(refreshToken -> refreshToken.replace(BEARER, ""))
        .orElseThrow(() -> new AuthException(INVALID_TOKEN));
  }
}
