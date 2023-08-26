package com.notificationapi.service;

import com.notificationapi.dto.RequestSmsTokenValidateDto;
import com.notificationapi.enums.TokenStatusEnum;
import com.notificationapi.exceptions.InvalidTokenExceptionHandler;
import com.notificationapi.repository.TokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsService.class);

    public ResponseEntity<String> tokenValidate(RequestSmsTokenValidateDto request) {
        LOGGER.info("Find token validate for phone number {}", request.phoneNumber());

        var token = tokenRepository.findLastTokenForNumber(request.phoneNumber());

        if (token == null) {
            LOGGER.info("Token is not found for phone number {}", request.phoneNumber());
            throw new InvalidTokenExceptionHandler();
        }


        if (token.isTokenValidated(request.contentToken())) {
            LOGGER.info("Token is valid for phone number {}", request.phoneNumber());

            token.setTokenStatusEnum(TokenStatusEnum.VALIDATED);
            tokenRepository.save(token);

            return new ResponseEntity<>(HttpStatusCode.valueOf(202));
        }

        LOGGER.info("Token is not validated for phone number {}", request.phoneNumber());
        throw new InvalidTokenExceptionHandler();
    }
}
