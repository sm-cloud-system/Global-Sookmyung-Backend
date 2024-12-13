package com.sookmyung.global.domain.auth.controller;

import jakarta.validation.*;

import org.springframework.http.*;

import com.sookmyung.global.common.response.*;
import com.sookmyung.global.domain.auth.dto.request.*;

public interface AuthApi {
  ResponseEntity<ResponseTemplate> createEmailCode(@Valid IssueEmailCodeRequest request);

  ResponseEntity<ResponseTemplate<?>> validateEmailCode(@Valid EmailVerificationRequest request);

  ResponseEntity<ResponseTemplate> signUp(@Valid SignUpRequest request);
}
