package com.notificationapi.dto;

import com.notificationapi.enums.OwnerRequest;
import jakarta.validation.constraints.NotBlank;

public record InputResquestSms(@NotBlank String phoneNumber, @NotBlank OwnerRequest ownerRequest) {
}
