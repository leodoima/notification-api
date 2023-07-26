package com.notificationapi.dto;

import com.notificationapi.enums.OwnerRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResquestSmsDto(@NotBlank String phoneNumber, @NotNull OwnerRequest ownerRequest) {
}
