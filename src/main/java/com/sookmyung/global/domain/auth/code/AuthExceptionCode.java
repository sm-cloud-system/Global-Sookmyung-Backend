package com.sookmyung.global.domain.auth.code;

import org.springframework.http.*;

import com.sookmyung.global.common.code.*;

import lombok.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AuthExceptionCode implements ExceptionCode {
  UNAUTHORIZED_TOKEN(HttpStatus.UNAUTHORIZED, "기한이 만료된 토큰입니다."),
  INVALID_TOKEN_HEADER(HttpStatus.BAD_REQUEST, "토큰 헤더가 존재하지 않습니다."),
  INVALID_TOKEN(HttpStatus.BAD_REQUEST, "서비스에서 발급되지 않은 토큰입니다."),
  INVALID_REQUEST_LOGIN(HttpStatus.BAD_REQUEST, "올바르지 않은 로그인 요청입니다"),
  INVALID_VERIFICATION_CODE(HttpStatus.BAD_REQUEST, "인증번호가 유효하지 않습니다"),
  INVALID_EMAIL_DOMAIN(HttpStatus.BAD_REQUEST, "숙명여자대학교 이메일이 아닙니다"),
  NOT_FOUND_EMAIL(HttpStatus.NOT_FOUND, "인증을 진행한 이메일을 찾을 수 없습니다");

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
