package com.sookmyung.global.domain.auth.dto.request;

import jakarta.validation.constraints.*;

public record IssueTokenRequest(@NotBlank String accessToken, @NotBlank String refreshToken) {}
