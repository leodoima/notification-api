package com.notificationapi.dto;

import jakarta.validation.constraints.NotBlank;

public record ResquestSmsDefaultDto(@NotBlank String phoneNumber) {
}
