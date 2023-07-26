package com.notificationapi.dto;

import com.notificationapi.enums.OwnerRequest;
import jakarta.validation.constraints.NotBlank;

public record ResquestSmsDto(@NotBlank String phoneNumber, @NotBlank OwnerRequest ownerRequest) {
}
