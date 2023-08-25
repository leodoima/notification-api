package com.notificationapi.service;

import com.notificationapi.dto.RequestSmsTokenValidateDto;
import com.notificationapi.dto.ResponseTokenDto;
import com.notificationapi.enums.TokenStatusEnum;
import com.notificationapi.repository.TokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TokenService {

    private static final String TOKEN_ACCEPTED = "Token is valid";

    private static final String TOKEN_REJECTED = "Token is not valid";


    @Autowired
    private TokenRepository tokenRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsService.class);

    public ResponseTokenDto tokenValidate(RequestSmsTokenValidateDto request) {
        LOGGER.info("Find token validate for phone number {}", request.phoneNumber());

        var token = tokenRepository.findLastTokenForNumber(request.phoneNumber());

        if (token == null) {
            LOGGER.info("Token is not found for phone number {}", request.phoneNumber());
            return new ResponseTokenDto(TOKEN_REJECTED, 401);
        }


        if (token.isTokenValidated(request.contentToken())) {
            LOGGER.info("Token is valid for phone number {}", request.phoneNumber());

            token.setTokenStatusEnum(TokenStatusEnum.VALIDATED);
            tokenRepository.save(token);

            return new ResponseTokenDto(TOKEN_ACCEPTED, 200);
        }

        LOGGER.info("Token is not validated for phone number {}", request.phoneNumber());
        return new ResponseTokenDto(TOKEN_REJECTED, 401);
    }
}
