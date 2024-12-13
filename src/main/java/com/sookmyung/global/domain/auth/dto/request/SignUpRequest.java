package com.sookmyung.global.domain.auth.dto.request;

import java.time.*;

import jakarta.validation.constraints.*;

public record SignUpRequest(
    @Email @NotBlank String email,
    @NotBlank String password,
    @NotBlank String firstName,
    @NotBlank String lastName,
    @NotNull LocalDate birthDate,
    @NotBlank String nickname,
    @NotNull Boolean isInternational,
    String nationalityName,
    String homeUniversityName) {}
