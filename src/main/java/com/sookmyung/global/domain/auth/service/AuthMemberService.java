package com.sookmyung.global.domain.auth.service;

import com.sookmyung.global.domain.auth.dto.request.*;
import com.sookmyung.global.domain.auth.dto.response.*;

public interface AuthMemberService {
  void SignUp(SignUpRequest request);

  IssueTokenResponse login(AuthRequest request);
}
