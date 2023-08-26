package com.notificationapi.builder;

import com.notificationapi.enums.TokenStatusEnum;
import com.notificationapi.model.Token;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Random;

@Service
public class TokenBuilder {

    private static final Integer ADD_SECONDS_EXPIRATION_CODE = 7200;

    private static final Integer MAX_VALUE_TOKEN = 999999;

    private static final Integer MIN_VALUE_TOKEN = 100000;


    public Token createToken() {

        var contentToken = generateToken();

        return Token.builder()
                .contentCode(contentToken)
                .hashToken(BCrypt.hashpw(contentToken, BCrypt.gensalt()))
                .createdAt(new Date())
                .expirationAt(Date.from(Instant.now().plusSeconds(ADD_SECONDS_EXPIRATION_CODE)))
                .tokenStatusEnum(TokenStatusEnum.CREATED)
                .build();
    }

    private String generateToken() {
        Random random = new Random();
        return String.valueOf(random.nextInt(MIN_VALUE_TOKEN, MAX_VALUE_TOKEN));
    }
}
