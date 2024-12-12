package com.sookmyung.global.external.jwt.provider;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.*;
import com.sookmyung.global.common.enums.*;

@Service
public class JwtTokenProviderImpl implements JwtTokenProvider {
  @Value("${jwt.secret-key}")
  private String secretKey;

  @Value("$jwt.access.guest-expiration")
  private Long accessTokenExpirationPeriodForGuest;

  @Value("${jwt.access.expiration}")
  private Long accessTokenExpirationPeriod;

  @Value("${jwt.refresh.expiration}")
  private Long refreshTokenExpirationPeriod;

  private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
  private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
  private static final String ID_CLAIM = "memberId";
  private static final String ROLE_CLAIM = "roles";

  @Override
  public String createAccessTokenForMember(final String memberId, final List<String> roles) {
    Date now = new Date();

    return JWT.create()
        .withSubject(ACCESS_TOKEN_SUBJECT)
        .withClaim(ID_CLAIM, memberId)
        .withClaim(ROLE_CLAIM, roles)
        .withExpiresAt(new Date(now.getTime() + accessTokenExpirationPeriod))
        .sign(Algorithm.HMAC512(secretKey));
  }

  @Override
  public String createAccessTokenForGuest() {
    List<String> roles = List.of(Role.GUEST.name());
    Date now = new Date();

    return JWT.create()
        .withClaim(ROLE_CLAIM, roles)
        .withExpiresAt(new Date(now.getTime() + accessTokenExpirationPeriodForGuest))
        .sign(Algorithm.HMAC512(secretKey));
  }

  @Override
  public String createRefreshToken() {
    Date now = new Date();

    return JWT.create()
        .withSubject(REFRESH_TOKEN_SUBJECT)
        .withExpiresAt(new Date(now.getTime() + refreshTokenExpirationPeriod))
        .sign(Algorithm.HMAC512(secretKey));
  }
}
