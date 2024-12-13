package com.sookmyung.global.domain.auth.service;

import static com.sookmyung.global.domain.auth.code.AuthExceptionCode.INVALID_EMAIL_DOMAIN;
import static com.sookmyung.global.domain.auth.code.AuthExceptionCode.INVALID_VERIFICATION_CODE;

import java.util.*;
import java.util.regex.*;

import org.springframework.stereotype.*;

import com.sookmyung.global.domain.auth.dto.request.*;
import com.sookmyung.global.domain.auth.dto.response.*;
import com.sookmyung.global.domain.auth.exception.*;
import com.sookmyung.global.external.email.service.*;
import com.sookmyung.global.external.jwt.provider.*;
import com.sookmyung.global.external.redis.dto.*;
import com.sookmyung.global.external.redis.repository.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class AuthEmailServiceImpl implements AuthEmailService {
  private static final int CODE_SIZE = 6;
  private static final int BOUND = 10;
  private static final Pattern EMAIL_PATTERN =
      Pattern.compile("^[a-zA-Z0-9._%+-]+@sookmyung\\.ac\\.kr$");
  private final RedisEmailCodeRepository redisEmailCodeRepository;
  private final EmailService emailService;
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public void createEmailCode(IssueEmailCodeRequest request) {
    validateEmail(request.email());
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
    validateEmail(request.email());
    EmailCodeDto foundEmailcodeDto = redisEmailCodeRepository.findByEmailOrThrow(request.email());
    String storedCode = foundEmailcodeDto.getCode();
    boolean isVerified = storedCode.equals(request.code());
    if (isVerified) {
      String accessTokenForGuest = jwtTokenProvider.createAccessTokenForGuest();
      return IssueTokenForGuestResponse.of(accessTokenForGuest);
    }
    throw new AuthException(INVALID_VERIFICATION_CODE);
  }

  private void validateEmail(String email) {
    boolean isNotSookMyungEmail = !EMAIL_PATTERN.matcher(email).matches();
    if (isNotSookMyungEmail) {
      throw new AuthException(INVALID_EMAIL_DOMAIN);
    }
  }
}
