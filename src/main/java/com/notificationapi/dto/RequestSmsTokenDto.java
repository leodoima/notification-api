package com.notificationapi.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestSmsTokenDto(@NotBlank String phoneNumber) {
}
