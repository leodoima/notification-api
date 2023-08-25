package com.notificationapi.builder;

import com.notificationapi.dto.RequestSmsTokenDto;
import com.notificationapi.enums.OwnerRequestEnum;
import com.notificationapi.enums.StatusSendEnum;
import com.notificationapi.enums.TokenTypeEnum;
import com.notificationapi.model.Sms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SmsBuilder {

    @Autowired
    private TokenBuilder tokenBuilder;

    public Sms smsToken(RequestSmsTokenDto request) {

        var token = tokenBuilder.createToken();

        String messageContent = generateMessageContent(request.tokenType(), token.getContentCode());

        return Sms.builder()
                .phoneNumber(request.phoneNumber())
                .messageContent(messageContent)
                .statusSendEnum(StatusSendEnum.REQUEST)
                .ownerRequestEnum(OwnerRequestEnum.DEFAULT)
                .createdAt(new Date())
                .tokenTypeEnum(request.tokenType())
                .token(token)
                .build();
    }


    private String generateMessageContent(TokenTypeEnum tokenType, String token) {

        switch (tokenType) {
            case RECOVER_ACCOUNT_TOKEN:
                return "Olá, segue seu código para recuperação de conta: " + token;
            case TWO_FACTOR_AUTHENTICATION:
                return "Olá, segue seu código para validação de acesso: " + token;
            case CONFIRM_PHONE_NUMBER_TOKEN:
                return "Olá, favor informar seu código para confirmação de telefone: " + token;
        }

        return null;
    }
}
