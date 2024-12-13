package com.sookmyung.global.domain.auth.controller;

import jakarta.validation.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.sookmyung.global.common.response.*;
import com.sookmyung.global.common.util.*;
import com.sookmyung.global.domain.auth.code.*;
import com.sookmyung.global.domain.auth.dto.request.*;
import com.sookmyung.global.domain.auth.dto.response.*;
import com.sookmyung.global.domain.auth.service.*;

import lombok.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi {
  private final AuthEmailService authEmailService;

  @PostMapping("/issue/code")
  @Override
  public ResponseEntity<ResponseTemplate> createEmailCode(
      @RequestBody @Valid IssueEmailCodeRequest request) {
    authEmailService.createEmailCode(request);
    return ResponseUtil.success(AuthSuccessCode.SUCCESS_CREATE_AND_SEND_EMAIL_CODE);
  }

  @RequestMapping("/verify/email")
  @Override
  public ResponseEntity<ResponseTemplate<?>> validateEmailCode(
      @RequestBody @Valid EmailVerificationRequest request) {
    IssueTokenForGuestResponse response = authEmailService.validateEmailCode(request);
    return ResponseUtil.success(AuthSuccessCode.SUCCESS_VALIDATE_EMAIL_CODE, response);
  }
}
