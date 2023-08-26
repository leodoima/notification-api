package com.notificationapi.controller;

import com.notificationapi.dto.RequestSmsTokenValidateDto;
import com.notificationapi.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token/validate")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/sms")
    public ResponseEntity<String> tokenValidate(@RequestBody @Valid RequestSmsTokenValidateDto requestValidateDto) {
        return tokenService.tokenValidate(requestValidateDto);
    }
}
