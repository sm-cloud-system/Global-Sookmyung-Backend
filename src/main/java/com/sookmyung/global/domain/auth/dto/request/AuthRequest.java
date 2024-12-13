package com.sookmyung.global.domain.auth.dto.request;

import jakarta.validation.constraints.*;

public record AuthRequest(@Email @NotBlank String email, @NotBlank String password) {}
