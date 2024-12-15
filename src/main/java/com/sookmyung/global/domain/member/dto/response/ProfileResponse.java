package com.sookmyung.global.domain.member.dto.response;

public record ProfileResponse(String nickname) {
  public static ProfileResponse of(String nickname) {
    return new ProfileResponse(nickname);
  }
}
