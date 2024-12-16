package com.sookmyung.global.common.code.success;

import org.springframework.http.*;

import lombok.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum MemberSuccessCode implements SuccessCode {
  SUCCESS_VALIDATE_NICKNAME(HttpStatus.OK, "닉네임 검증에 성공했습니다"),
  SUCCESS_GET_PROFILE(HttpStatus.OK, "프로필 조회에 성공했습니다"),
  SUCCESS_GET_MEMBER_POSTS(HttpStatus.OK, "사용자가 작성한 게시글 리스트 조회에 성공했습니다"),
  SUCCESS_GET_MEMBER_BOOKMARK_POSTS(HttpStatus.OK, "사용자가 즐겨찾기 한 게시글 리스트 조회에 성공했습니다");

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
