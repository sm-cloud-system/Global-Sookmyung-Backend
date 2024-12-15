package com.sookmyung.global.common.code.success;

import org.springframework.http.*;

import lombok.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum BookmarkSuccessCode implements SuccessCode {
  SUCCESS_CREATE_BOOKMARK(HttpStatus.CREATED, "즐겨찾기 생성에 성공했습니다"),
  SUCCESS_DELETE_BOOKMARK(HttpStatus.OK, "즐겨찾기 삭제에 성공했습니다");

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
