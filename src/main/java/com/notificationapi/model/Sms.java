package com.notificationapi.model;

import com.notificationapi.enums.OwnerRequestEnum;
import com.notificationapi.enums.SmsTypeEnum;
import com.notificationapi.enums.SmsStatusSendEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Sms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "token_id", referencedColumnName = "id")
    private Token token;

    @NotBlank
    private String phoneNumber;

    @Transient
    @NotBlank
    private String messageContent;

    @NotNull
    @CreatedDate
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "sms_type")
    private SmsTypeEnum smsTypeEnum;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "owner_request")
    private OwnerRequestEnum ownerRequestEnum;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "sms_status_send")
    private SmsStatusSendEnum smsStatusSendEnum;
}

