package com.sookmyung.global.external.jwt.validator;

import static com.sookmyung.global.common.code.fail.AuthExceptionCode.INVALID_TOKEN;
import static com.sookmyung.global.common.code.fail.AuthExceptionCode.UNAUTHORIZED_TOKEN;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.*;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.sookmyung.global.common.exception.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class JwtTokenValidatorImpl implements JwtTokenValidator {

  @Value("${jwt.secret-key}")
  private String secretKey;

  private JWTVerifier getVerifier() {
    return JWT.require(Algorithm.HMAC512(secretKey)).acceptExpiresAt(0).build();
  }

  @Override
  public void validateToken(final String token) {
    try {
      getVerifier().verify(token);
    } catch (TokenExpiredException e) {
      throw new AuthException(UNAUTHORIZED_TOKEN);
    } catch (SignatureVerificationException | JWTDecodeException e) {
      throw new AuthException(INVALID_TOKEN);
    }
  }

  @Override
  public Claim getClaim(final String token, final String claim) {
    return getVerifier().verify(token).getClaim(claim);
  }
}
