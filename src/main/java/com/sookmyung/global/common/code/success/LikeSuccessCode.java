package com.sookmyung.global.common.code.success;

import org.springframework.http.*;

import lombok.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum LikeSuccessCode implements SuccessCode {
  SUCCESS_CREATE_LIKE(HttpStatus.CREATED, "좋아요 생성에 성공했습니다"),
  SUCCESS_DELETE_LIKE(HttpStatus.OK, "좋아요 삭제에 성공했습니다");

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
