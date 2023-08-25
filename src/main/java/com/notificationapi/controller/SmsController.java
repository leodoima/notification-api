package com.notificationapi.controller;

import com.notificationapi.dto.RequestSmsTokenDto;
import com.notificationapi.dto.ResponseSmsDto;
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

    @PostMapping("/token")
    public ResponseEntity<ResponseSmsDto> sendToken(@RequestBody @Valid RequestSmsTokenDto request) {
        smsService.requestToken(request);
        return ResponseEntity.ok().build();
    }
}
