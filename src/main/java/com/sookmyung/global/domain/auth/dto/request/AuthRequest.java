package com.sookmyung.global.domain.auth.dto.request;

import jakarta.validation.constraints.*;

public record AuthRequest(@NotBlank String email, @NotBlank String password) {}
