package com.notificationapi.model.sms;

import com.notificationapi.enums.OwnerRequest;
import com.notificationapi.enums.SmsType;
import com.notificationapi.enums.StatusSendNotification;
import lombok.*;

@Data
@Getter
@Setter
public abstract class SmsModel {

    private Long id;
    private SmsType smsType;
    private String phoneNumber;
    private OwnerRequest ownerRequest;
    private StatusSendNotification statusSendNotification;

    public SmsModel(String phoneNumber, SmsType smsType, OwnerRequest ownerRequest) {
        this.smsType = smsType;
        this.phoneNumber = phoneNumber;
        this.ownerRequest = ownerRequest;
        this.statusSendNotification = StatusSendNotification.REQUEST;
    }

    public abstract String messageDescription();
}
