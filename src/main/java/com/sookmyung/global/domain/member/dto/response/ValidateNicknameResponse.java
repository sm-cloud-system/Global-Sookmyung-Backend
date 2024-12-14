package com.sookmyung.global.domain.member.dto.response;

public record ValidateNicknameResponse(Boolean isAvailable) {
  public static ValidateNicknameResponse of(boolean isAvailable) {
    return new ValidateNicknameResponse(isAvailable);
  }
}
