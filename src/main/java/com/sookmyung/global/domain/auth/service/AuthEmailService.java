package com.sookmyung.global.domain.auth.service;

import com.sookmyung.global.domain.auth.dto.request.*;
import com.sookmyung.global.domain.auth.dto.response.*;

public interface AuthEmailService {
  void createEmailCode(IssueEmailCodeRequest request);

  IssueTokenForGuestResponse validateEmailCode(EmailVerificationRequest request);
}
