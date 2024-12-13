package com.sookmyung.global.domain.university.code;

import org.springframework.http.*;

import com.sookmyung.global.common.code.*;

import lombok.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum UniversityExceptionCode implements ExceptionCode {
  NOT_FOND_UNIVERSITY(HttpStatus.NOT_FOUND, "해당하는 대학을 찾을 수 없습니다");

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
