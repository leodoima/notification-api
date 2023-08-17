package com.notificationapi.builder;

import com.notificationapi.enums.OwnerRequestEnum;
import com.notificationapi.enums.SmsStatusSendEnum;
import com.notificationapi.model.Token;
import org.springframework.stereotype.Service;

@Service
public class TokenBuilder {

    public Token createToken() {
        return Token.builder().build();
    }
}
