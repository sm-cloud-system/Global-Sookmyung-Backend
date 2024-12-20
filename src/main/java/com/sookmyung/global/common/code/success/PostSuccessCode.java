package com.sookmyung.global.common.code.success;

import org.springframework.http.*;

import lombok.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum PostSuccessCode implements SuccessCode {
  SUCCESS_GET_POST(HttpStatus.OK, "게시글 상세조회에 성공했습니다"),
  SUCCESS_GET_POST_BY_SEARCHING(HttpStatus.OK, "게시글 검색에 성공했습니다"),
  SUCCESS_GET_POSTS(HttpStatus.OK, "게시판 별 게시글 리스트 조회에 성공했습니다"),
  SUCCESS_CREATE_POST(HttpStatus.CREATED, "게시글 생성에 성공했습니다.");

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
