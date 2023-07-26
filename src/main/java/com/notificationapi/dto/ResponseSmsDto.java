package com.notificationapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponseSmsDto(@NotBlank String description, @NotNull Integer statusCode) {
}
