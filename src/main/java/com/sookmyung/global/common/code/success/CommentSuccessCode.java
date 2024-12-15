package com.sookmyung.global.common.code.success;

import org.springframework.http.*;

import lombok.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CommentSuccessCode implements SuccessCode {
  SUCCESS_CREATE_COMMENT(HttpStatus.CREATED, "댓글 생성에 성공했습니다");

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
