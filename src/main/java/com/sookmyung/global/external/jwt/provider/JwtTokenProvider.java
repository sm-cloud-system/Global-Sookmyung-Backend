package com.sookmyung.global.external.jwt.provider;

import java.util.*;

public interface JwtTokenProvider {
  String createAccessTokenForMember(String memberId, List<String> roles);

  String createAccessTokenForGuest();

  String createRefreshToken();
}
