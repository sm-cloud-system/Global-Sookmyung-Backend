package com.sookmyung.global.external.jwt.validator;

import com.auth0.jwt.interfaces.*;

public interface JwtTokenValidator {
  void validateToken(String token);

  Claim getClaim(String token, String claim);
}
