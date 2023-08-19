package com.notificationapi.service;

import com.notificationapi.dto.RequestSmsTokenDto;
import com.notificationapi.dto.RequestSmsTokenValidateDto;
import com.notificationapi.enums.SmsTypeEnum;
import com.notificationapi.builder.SmsBuilder;
import com.notificationapi.dto.ResponseSmsDto;
import com.notificationapi.dto.ResquestSmsDefaultDto;

import com.notificationapi.enums.TokenStatusEnum;
import com.notificationapi.model.Token;
import com.notificationapi.repository.SmsRepository;
import com.notificationapi.repository.TokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.security.crypto.bcrypt.BCrypt.hashpw;

@Service
public class SmsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsService.class);

    @Autowired
    private TotalVoiceService totalVoiceService;
    @Autowired
    private SmsRepository smsRepository;

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private SmsBuilder smsBuilder;

    public ResponseSmsDto requestToken(SmsTypeEnum smsTypeEnum, RequestSmsTokenDto request) {
        var smsRequest = smsBuilder.smsToken(smsTypeEnum, request);
        smsRepository.save(smsRequest);

        return totalVoiceService.sendSms(smsRequest);
    }

    public ResponseSmsDto tokenValidate(RequestSmsTokenValidateDto request) {
        LOGGER.info("Find token validate for phone number {}", request.phoneNumber());

        var token = tokenRepository.findLastTokenForNumber(request.phoneNumber());

        if (!checkToken(token)) return new ResponseSmsDto("Token is not valid", 401);


        if (token.isTokenChecked(request.contentToken())) {
            LOGGER.info("Token is valid for phone number {}", request.phoneNumber());

            token.setTokenStatusEnum(TokenStatusEnum.VALIDATED);
            tokenRepository.save(token);

            return new ResponseSmsDto("Token is valid", 200);
        }

        LOGGER.info("Token is not found for phone number {}", request.phoneNumber());
        return new ResponseSmsDto("Token is not valid", 401);
    }

    private boolean checkToken(Token token) {

        if (token == null) {
            LOGGER.info("Not found token by phone");
            return false;
        }

        if (!token.isTokenValid()) {
            LOGGER.info("Token is not valid");
            return false;
        }

        return true;
    }
}
