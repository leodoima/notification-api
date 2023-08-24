package com.notificationapi.controller;

import com.notificationapi.dto.RequestSmsTokenDto;
import com.notificationapi.dto.RequestSmsTokenValidateDto;
import com.notificationapi.dto.ResponseSmsDto;
import com.notificationapi.enums.SmsTypeEnum;
import com.notificationapi.service.SmsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("/account/recover")
    public ResponseEntity<ResponseSmsDto> accountRecover(@RequestBody @Valid RequestSmsTokenDto requestSmsTokenDto) {
        var responseSmsDto = smsService.requestToken(SmsTypeEnum.RECOVER_ACCOUNT_TOKEN, requestSmsTokenDto);
        return ResponseEntity.status(responseSmsDto.statusCode()).body(responseSmsDto);
    }

    @PostMapping("/phone/confirm")
    public ResponseEntity<ResponseSmsDto> phoneValidate(@RequestBody @Valid RequestSmsTokenDto requestSmsTokenDto) {
        var responseSmsDto = smsService.requestToken(SmsTypeEnum.CONFIRM_PHONE_NUMBER_TOKEN, requestSmsTokenDto);
        return ResponseEntity.status(responseSmsDto.statusCode()).body(responseSmsDto);
    }
}
