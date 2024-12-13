package com.sookmyung.global.domain.auth.service;

import org.springframework.stereotype.*;

import com.sookmyung.global.domain.auth.dto.request.*;
import com.sookmyung.global.domain.auth.dto.response.*;
import com.sookmyung.global.external.redis.dto.*;
import com.sookmyung.global.external.redis.repository.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final RedisTokenRepository redisTokenRepository;

  @Override
  public void createEmailCode(IssueEmailCodeRequest request) {}

  @Override
  public IssueTokenForGuestResponse validateEmailCode(EmailVerificationRequest request) {
    return null;
  }
}
