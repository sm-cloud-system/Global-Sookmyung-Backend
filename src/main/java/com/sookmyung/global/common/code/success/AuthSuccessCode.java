package com.sookmyung.global.common.code.success;

import org.springframework.http.*;

import lombok.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AuthSuccessCode implements SuccessCode {
  SUCCESS_CREATE_AND_SEND_EMAIL_CODE(HttpStatus.OK, "이메일 인증코드 생성 및 전송에 성공했습니다"),
  SUCCESS_VALIDATE_EMAIL_CODE(HttpStatus.OK, "인증이 완료되었습니다"),
  SUCCESS_SIGN_UP(HttpStatus.CREATED, "회원가입에 성공했습니다"),
  SUCCESS_LOGIN_IN(HttpStatus.OK, "로그인에 성공했습니다");

  private final HttpStatus status;
  private final String message;

  @Override
  public HttpStatus status() {
    return this.status;
  }

  @Override
  public String message() {
    return this.message;
  }
}
