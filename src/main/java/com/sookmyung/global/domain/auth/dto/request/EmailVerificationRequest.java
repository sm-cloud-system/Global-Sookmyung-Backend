package com.sookmyung.global.domain.auth.dto.request;

import jakarta.validation.constraints.*;

public record EmailVerificationRequest(@Email @NotBlank String email, @NotBlank String code) {}
