package com.notificationapi.model;


import com.notificationapi.enums.TokenTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static com.notificationapi.enums.TokenTypeEnum.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
public class Sms extends SenderToken {

    @NotBlank
    private String phoneNumber;

    @Transient
    @NotBlank
    private String messageContent;
}

