package com.notificationapi.model.sms;

import com.notificationapi.enums.OwnerRequest;
import com.notificationapi.enums.SmsType;

public class SmsWarningModel extends SmsModel {

    public SmsWarningModel(String phoneNumber, SmsType smsType, OwnerRequest ownerRequest) {
        super(phoneNumber, smsType, ownerRequest);
    }

    @Override
    public String messageDescription() {
        return String.valueOf(super.getSmsType());
    }
}
