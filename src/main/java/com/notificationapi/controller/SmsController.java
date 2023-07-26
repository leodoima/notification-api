package com.notificationapi.controller;

import com.notificationapi.dto.ResponseSmsDto;
import com.notificationapi.dto.ResquestSmsDto;
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

    @PostMapping("/recover")
    public ResponseEntity<ResponseSmsDto> recover(@RequestBody @Valid ResquestSmsDto resquestSmsDto) {
        ResponseSmsDto responseSmsDto = smsService.smsRecover(resquestSmsDto);
        return ResponseEntity.status(responseSmsDto.statusCode()).body(responseSmsDto);
    }
}
