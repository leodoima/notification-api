package com.notificationapi.builder;

import com.notificationapi.dto.RequestSmsTokenDto;
import com.notificationapi.dto.ResquestSmsDefaultDto;
import com.notificationapi.enums.OwnerRequestEnum;
import com.notificationapi.enums.SmsTypeEnum;
import com.notificationapi.enums.SmsStatusSendEnum;
import com.notificationapi.model.Sms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsBuilder {

    @Autowired
    private TokenBuilder tokenBuilder;

    public Sms smsDefault(SmsTypeEnum smsTypeEnum, ResquestSmsDefaultDto resquestSmsDefaultDto) {
        return Sms.builder()
                .smsTypeEnum(smsTypeEnum)
                .token(null)
                .ownerRequestEnum(OwnerRequestEnum.DEFAULT)
                .phoneNumber(resquestSmsDefaultDto.phoneNumber())
                .messageContent(smsTypeEnum.getMessageDescription())
                .smsStatusSendEnum(SmsStatusSendEnum.REQUEST)
                .build();
    }

    public Sms smsToken(SmsTypeEnum smsTypeEnum, RequestSmsTokenDto requestSmsTokenDto) {

        var token = tokenBuilder.createToken();

        String messageContent = smsTypeEnum.getMessageDescription() + token.getContentCode();

        return Sms.builder()
                .smsTypeEnum(smsTypeEnum)
                .token(token)
                .ownerRequestEnum(OwnerRequestEnum.DEFAULT)
                .phoneNumber(requestSmsTokenDto.phoneNumber())
                .messageContent(messageContent)
                .smsStatusSendEnum(SmsStatusSendEnum.REQUEST)
                .build();
    }
}
