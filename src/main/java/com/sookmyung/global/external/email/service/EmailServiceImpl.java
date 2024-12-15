package com.sookmyung.global.external.email.service;

import static com.sookmyung.global.common.code.CommonExceptionCode.EXTERNAL_SERVER_ERROR;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;

import com.sookmyung.global.common.exception.*;

import lombok.*;
import lombok.extern.slf4j.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
  @Value("${spring.mail.username}")
  private String fromEmail;

  private final JavaMailSender mailSender;
  private static final String SUBJECT_STRING_TEMPLATE = "[GLOBAL SOOKMYUNG] 메일 인증 안내";
  private static final String CONTENT_TEMPLATE =
      "<div style='font-size: 20px;'>인증코드는 <b>%s</b> 입니다.</div>";

  @Override
  public void sendEmailVerificationCode(String email, String code) {
    MimeMessage mimeMessage = mailSender.createMimeMessage();

    try {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
      String content = CONTENT_TEMPLATE.formatted(code);

      messageHelper.setTo(email);
      messageHelper.setFrom(fromEmail);
      messageHelper.setSubject(SUBJECT_STRING_TEMPLATE);
      messageHelper.setText(content, true);
      mailSender.send(mimeMessage);
    } catch (RuntimeException | MessagingException e) {
      log.error("이메일 전송 오류: " + e.getMessage());
      throw new CustomException(EXTERNAL_SERVER_ERROR);
    }
  }
}
