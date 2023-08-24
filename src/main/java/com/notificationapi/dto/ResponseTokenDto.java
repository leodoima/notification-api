package com.notificationapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponseTokenDto(@NotBlank String description, @NotNull Integer statusCode) {
}
