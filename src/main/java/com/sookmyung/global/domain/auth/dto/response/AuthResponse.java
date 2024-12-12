package com.sookmyung.global.domain.auth.dto.response;

import com.sookmyung.global.common.enums.*;

public record AuthResponse(Long memberId, Role role, IssueTokenResponse token) {
  public static AuthResponse of(Long memberId, Role role, IssueTokenResponse issueTokenResponse) {
    return new AuthResponse(memberId, role, issueTokenResponse);
  }
}
