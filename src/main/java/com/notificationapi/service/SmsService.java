package com.notificationapi.service;

import com.notificationapi.dto.RequestSmsTokenDto;
import com.notificationapi.enums.SmsTypeEnum;
import com.notificationapi.builder.SmsBuilder;
import com.notificationapi.dto.ResponseSmsDto;
import com.notificationapi.repository.SmsRepository;
import com.notificationapi.repository.TokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
