package com.notificationapi.dto;

import com.notificationapi.enums.TokenTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestSmsTokenDto(@NotBlank String phoneNumber, @NotNull TokenTypeEnum tokenType) {
}
