package com.sookmyung.global.common.config;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.mail.javamail.*;

@Configuration
public class EmailConfig {
  @Value("${spring.mail.host}")
  private String mailServerHost;

  @Value("${spring.mail.port}")
  private int mailServerPort;

  @Value("${spring.mail.username}")
  private String mailServerUsername;

  @Value("${spring.mail.password}")
  private String mailServerPassword;

  @Value("${spring.mail.properties.mail.smtp.auth}")
  private String mailServerAuth;

  @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
  private String mailServerStarttlsEnable;

  private static final String MAIL_SERVER_PROTOCOL = "smtp";

  @Bean
  public JavaMailSender javaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(mailServerHost);
    mailSender.setPort(mailServerPort);
    mailSender.setUsername(mailServerUsername);
    mailSender.setPassword(mailServerPassword);

    Properties properties = mailSender.getJavaMailProperties();
    properties.put("mail.transport.protocol", MAIL_SERVER_PROTOCOL);
    properties.put("mail.smtp.auth", mailServerAuth);
    properties.put("mail.smtp.starttls.enable", mailServerStarttlsEnable);

    return mailSender;
  }
}
