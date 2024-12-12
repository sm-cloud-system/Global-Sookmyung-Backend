package com.sookmyung.global.domain.auth.dto.request;

import jakarta.validation.constraints.*;

public record EmailVerificationRequest(@NotBlank String email, @NotBlank String code) {}
