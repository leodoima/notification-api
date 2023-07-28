package com.notificationapi.model;

import com.notificationapi.enums.OwnerRequest;
import com.notificationapi.enums.SmsType;
import com.notificationapi.model.Sms;
import lombok.*;


@Getter
@Setter
public class SmsWarning extends Sms {

    public SmsWarning(String phoneNumber, SmsType smsType, OwnerRequest ownerRequest) {
        super(phoneNumber, smsType, ownerRequest);
    }

    @Override
    public String messageDescription() {
        return String.valueOf(super.getSmsType());
    }
}
