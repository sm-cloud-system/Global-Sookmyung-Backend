package com.sookmyung.global.domain.auth.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.sookmyung.global.common.response.*;
import com.sookmyung.global.domain.auth.dto.request.*;
import com.sookmyung.global.domain.auth.dto.response.*;

import lombok.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi {
  @Override
  public ResponseEntity createEmailCode(IssueEmailCodeRequest request) {
    return null;
  }

  @Override
  public ResponseEntity<ResponseTemplate<IssueTokenForGuestResponse>> validateEmailCode(
      EmailVerificationRequest request) {
    return null;
  }
}
