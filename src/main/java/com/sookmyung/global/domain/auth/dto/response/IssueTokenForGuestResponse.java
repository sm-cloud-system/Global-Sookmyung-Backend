package com.sookmyung.global.domain.auth.dto.response;

public record IssueTokenForGuestResponse(String accessToken) {
  public static IssueTokenForGuestResponse of(String accessToken) {
    return new IssueTokenForGuestResponse(accessToken);
  }
}
