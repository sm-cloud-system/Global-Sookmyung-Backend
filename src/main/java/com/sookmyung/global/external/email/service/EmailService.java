package com.sookmyung.global.external.email.service;

public interface EmailService {
  void sendEmailVerificationCode(String email, String code);
}
