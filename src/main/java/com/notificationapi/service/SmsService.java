package com.notificationapi.service;

import com.notificationapi.dto.InputResquestSms;
import com.notificationapi.enums.SmsType;
import com.notificationapi.model.sms.SmsTokenModel;
import jakarta.validation.Valid;

public class SmsService {

    public void smsRecover(InputResquestSms inputResquestSms) {
        SmsTokenModel sms = new SmsTokenModel(inputResquestSms.phoneNumber(), SmsType.RECOVER_ACCOUNT_TOKEN, inputResquestSms.ownerRequest());

    }

    public void smsValidate(InputResquestSms inputResquestSms) {
    }

    public void smsCreateAccount(InputResquestSms inputResquestSms) {
    }
}
