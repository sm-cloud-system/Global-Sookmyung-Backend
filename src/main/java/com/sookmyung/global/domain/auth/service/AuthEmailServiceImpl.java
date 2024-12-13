package com.sookmyung.global.domain.auth.service;

import static com.sookmyung.global.domain.auth.code.AuthExceptionCode.INVALID_VERIFICATION_CODE;

import java.util.*;

import org.springframework.stereotype.*;

import com.sookmyung.global.domain.auth.code.*;
import com.sookmyung.global.domain.auth.dto.request.*;
import com.sookmyung.global.domain.auth.dto.response.*;
import com.sookmyung.global.domain.auth.exception.*;
import com.sookmyung.global.external.email.service.*;
import com.sookmyung.global.external.jwt.provider.*;
import com.sookmyung.global.external.redis.dto.*;
import com.sookmyung.global.external.redis.repository.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class AuthEmailServiceImpl implements AuthEmailService {
  private static final int CODE_SIZE = 6;
  private static final int BOUND = 10;
  private final RedisEmailCodeRepository redisEmailCodeRepository;
  private final EmailService emailService;
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public void createEmailCode(IssueEmailCodeRequest request) {
    EmailCodeDto codeDto = createEmailVerificationCode(request.email());
    redisEmailCodeRepository.save(codeDto);
    emailService.sendEmailVerificationCode(request.email(), codeDto.getCode());
  }

  private EmailCodeDto createEmailVerificationCode(String email) {
    Random random = new Random();
    StringBuilder codeBuilder = new StringBuilder(CODE_SIZE);
    for (int seq = 0; seq < CODE_SIZE; seq++) {
      codeBuilder.append(random.nextInt(BOUND));
    }
    String emailVerificationCode = codeBuilder.toString();
    return EmailCodeDto.builder().email(email).code(emailVerificationCode).build();
  }

  @Override
  public IssueTokenForGuestResponse validateEmailCode(EmailVerificationRequest request) {
    EmailCodeDto foundEmailcodeDto = redisEmailCodeRepository.findByEmailOrThrow(request.email());
    boolean isVerified = request.email().equals(foundEmailcodeDto.getCode());

    if (isVerified) {
      String accessTokenForGuest = jwtTokenProvider.createAccessTokenForGuest();
      return IssueTokenForGuestResponse.of(accessTokenForGuest);
    }
    throw new AuthException(INVALID_VERIFICATION_CODE);
  }
}
