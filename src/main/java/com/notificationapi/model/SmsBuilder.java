package com.notificationapi.model;

import com.notificationapi.dto.RequestSmsTokenDto;
import com.notificationapi.dto.ResquestSmsDefaultDto;
import com.notificationapi.enums.OwnerRequestEnum;
import com.notificationapi.enums.SmsTypeEnum;
import com.notificationapi.enums.SmsStatusSendEnum;
import org.springframework.stereotype.Service;

@Service
public class SmsBuilder {

    public Sms smsDefaultBuilder(SmsTypeEnum smsTypeEnum, ResquestSmsDefaultDto resquestSmsDefaultDto) {
        return Sms.builder()
                .smsTypeEnum(smsTypeEnum)
                .token(null)
                .ownerRequestEnum(OwnerRequestEnum.DEFAULT)
                .phoneNumber(resquestSmsDefaultDto.phoneNumber())
                .messageContent(smsTypeEnum.getMessageDescription())
                .smsStatusSendEnum(SmsStatusSendEnum.REQUEST)
                .build();
    }

    public Sms smsTokenBuilder(SmsTypeEnum smsTypeEnum, RequestSmsTokenDto requestSmsTokenDto) {

        Token token = new Token();

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
