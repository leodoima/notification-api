package com.notificationapi.service;

import com.notificationapi.dto.RequestSmsTokenDto;
import com.notificationapi.dto.RequestSmsTokenValidateDto;
import com.notificationapi.enums.SmsTypeEnum;
import com.notificationapi.model.Sms;
import com.notificationapi.model.SmsBuilder;
import com.notificationapi.dto.ResponseSmsDto;
import com.notificationapi.dto.ResquestSmsDefaultDto;

import com.notificationapi.repository.SmsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.security.crypto.bcrypt.BCrypt.hashpw;

@Service
public class SmsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsService.class);

    @Autowired
    private TotalVoiceService totalVoiceService;
    @Autowired
    private SmsRepository smsRepository;
    @Autowired
    private SmsBuilder smsBuilder;

    public ResponseSmsDto requestToken(SmsTypeEnum smsTypeEnum, RequestSmsTokenDto request) {
        Sms smsRequest = smsBuilder.smsTokenBuilder(smsTypeEnum, request);
        smsRepository.save(smsRequest);

        return totalVoiceService.sendSms(smsRequest);
    }

    public ResponseSmsDto smsDefault(SmsTypeEnum smsTypeEnum, ResquestSmsDefaultDto request) {
        Sms smsRequest = smsBuilder.smsDefaultBuilder(smsTypeEnum, request);
        smsRepository.save(smsRequest);

        return totalVoiceService.sendSms(smsRequest);
    }

    public ResponseSmsDto tokenValidate(RequestSmsTokenValidateDto request) {
        LOGGER.info("Find token validate for phone number {}", request.phoneNumber());

        Sms sms = smsRepository.findById(1L).get();

        if (sms == null) {
            LOGGER.info("Find token by phone return null");
            return new ResponseSmsDto("Token is not valid", 401);
        }

        if (sms.getToken().isTokenExpired()) {
            LOGGER.info("Last token for phone {} is expired", request.phoneNumber());
            return new ResponseSmsDto("Token is not valid", 401);
        }

        if (sms.getToken().isTokenValid(request.contentToken())) {
            LOGGER.info("Token is valid for phone number {}", request.phoneNumber());
            return new ResponseSmsDto("Token is valid", 200);
        }

        LOGGER.info("Token is not found for phone number {}", request.phoneNumber());

        return new ResponseSmsDto("Token is not valid", 401);
    }
}
