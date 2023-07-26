package com.notificationapi.model.sms;

import com.notificationapi.enums.OwnerRequest;
import com.notificationapi.enums.SmsType;
import com.notificationapi.model.token.TokenModel;
import lombok.*;

@Getter
@Setter
public class SmsTokenModel extends SmsModel {

    private TokenModel token;

    public SmsTokenModel(String phoneNumber, SmsType smsType, OwnerRequest ownerRequest) {
        super(phoneNumber, smsType, ownerRequest);
        this.token = new TokenModel();
    }

    @Override
    public String messageDescription() {
        return super.getSmsType() + token.getContentCode();
    }
}
