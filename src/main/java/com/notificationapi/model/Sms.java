package com.notificationapi.model;

import com.notificationapi.enums.OwnerRequest;
import com.notificationapi.enums.SmsType;
import com.notificationapi.enums.StatusSendNotification;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@MappedSuperclass
public abstract class Sms {

    private Long id;
    private SmsType smsType;
    private String phoneNumber;
    private OwnerRequest ownerRequest;
    private StatusSendNotification statusSendNotification;

    public Sms(String phoneNumber, SmsType smsType, OwnerRequest ownerRequest) {
        this.smsType = smsType;
        this.phoneNumber = phoneNumber;
        this.ownerRequest = ownerRequest;
        this.statusSendNotification = StatusSendNotification.REQUEST;
    }

    public abstract String messageDescription();
}

