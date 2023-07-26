package com.notificationapi.service;

import com.notificationapi.dto.ResponseSmsDto;
import com.notificationapi.dto.ResquestSmsDto;
import com.notificationapi.enums.SmsType;
import com.notificationapi.model.sms.SmsTokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Autowired
    TotalVoiceService totalVoiceService;

    public ResponseSmsDto smsRecover(ResquestSmsDto resquestSmsDto) {
        SmsTokenModel smsRequest = new SmsTokenModel(resquestSmsDto.phoneNumber(), SmsType.RECOVER_ACCOUNT_TOKEN, resquestSmsDto.ownerRequest());
        // salvar smsRequest
        return totalVoiceService.sendSms(smsRequest);
    }

    public void smsValidate(ResquestSmsDto resquestSmsDto) {
    }

    public void smsCreateAccount(ResquestSmsDto resquestSmsDto) {
    }
}
