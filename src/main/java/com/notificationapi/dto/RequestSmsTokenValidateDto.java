package com.notificationapi.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestSmsTokenValidateDto(@NotBlank String phoneNumber, @NotBlank String contentToken) {
}
