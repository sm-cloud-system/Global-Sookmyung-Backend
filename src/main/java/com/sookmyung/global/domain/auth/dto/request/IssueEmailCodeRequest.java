package com.sookmyung.global.domain.auth.dto.request;

import jakarta.validation.constraints.*;

public record IssueEmailCodeRequest(@NotBlank String email) {}
