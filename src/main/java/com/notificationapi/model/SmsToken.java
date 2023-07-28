package com.notificationapi.model;

import com.notificationapi.enums.OwnerRequest;
import com.notificationapi.enums.SmsType;
import lombok.*;

@Getter
@Setter
public class SmsToken extends Sms {

    private Token token;

    public SmsToken(String phoneNumber, SmsType smsType, OwnerRequest ownerRequest) {
        super(phoneNumber, smsType, ownerRequest);
        this.token = new Token();
    }

    @Override
    public String messageDescription() {
        return super.getSmsType() + token.getContentCode();
    }
}
