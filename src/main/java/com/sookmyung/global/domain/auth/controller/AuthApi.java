package com.sookmyung.global.domain.auth.controller;

import org.springframework.http.*;

import com.sookmyung.global.common.response.*;
import com.sookmyung.global.domain.auth.dto.request.*;
import com.sookmyung.global.domain.auth.dto.response.*;

public interface AuthApi {
  ResponseEntity createEmailCode(IssueEmailCodeRequest request);

  ResponseEntity<ResponseTemplate<IssueTokenForGuestResponse>> validateEmailCode(
      EmailVerificationRequest request);
}
