package com.sookmyung.global.domain.auth.service;

import java.util.*;

import org.springframework.stereotype.*;

import com.sookmyung.global.domain.auth.dto.request.*;
import com.sookmyung.global.domain.auth.dto.response.*;
import com.sookmyung.global.external.email.service.*;
import com.sookmyung.global.external.redis.dto.*;
import com.sookmyung.global.external.redis.repository.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class AuthEmailServiceImpl implements AuthEmailService {
  private static final int CODE_SIZE = 6;
  private static final int BOUND = 10;
  private final RedisEmailCodeRepository redisEmailCodeRepository;
  private final EmailService emailService;

  @Override
  public void createEmailCode(IssueEmailCodeRequest request) {
    EmailCodeDto codeDto = createEmailVerificationCode(request.email());
    redisEmailCodeRepository.save(codeDto);
    emailService.sendEmailVerificationCode(request.email(), codeDto.getCode());
  }

  private EmailCodeDto createEmailVerificationCode(String email) {
    Random random = new Random();
    StringBuilder codeBuilder = new StringBuilder(CODE_SIZE);
    for (int seq = 0; seq < CODE_SIZE; seq++) {
      codeBuilder.append(random.nextInt(BOUND));
    }
    String emailVerificationCode = codeBuilder.toString();
    return EmailCodeDto.builder().email(email).code(emailVerificationCode).build();
  }

  @Override
  public IssueTokenForGuestResponse validateEmailCode(EmailVerificationRequest request) {
    return null;
  }
}
