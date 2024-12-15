package com.sookmyung.global.common.code.fail;

import org.springframework.http.*;

import lombok.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum PostExceptionCode implements ExceptionCode {
  INVALID_CREATE_POST_REQUEST(HttpStatus.BAD_REQUEST, "올바르지 않은 게시글 생성 요청입니다"),
  NOT_FOUND_POST(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다");

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
