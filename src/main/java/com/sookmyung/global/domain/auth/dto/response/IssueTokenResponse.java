package com.sookmyung.global.domain.auth.dto.response;

public record IssueTokenResponse(String accessToken, String refreshToken) {
  public static IssueTokenResponse of(String accessToken, String refreshToken) {
    return new IssueTokenResponse(accessToken, refreshToken);
  }
}
