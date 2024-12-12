package com.sookmyung.global.external.jwt.service;

import java.util.*;

import jakarta.servlet.http.*;

import com.sookmyung.global.domain.auth.dto.request.*;
import com.sookmyung.global.domain.auth.dto.response.*;

public interface JwtService {
  IssueTokenResponse issueToken(String memberId, List<String> roles);

  IssueTokenResponse reissueToken(IssueTokenRequest request);

  String extractAccessToken(HttpServletRequest request);

  String extractRefreshToken(HttpServletRequest request);

  String getMemberIdFromAccessToken(String accessToken);

  List<String> getRolesFromAccessToken(String accessToken);
}
