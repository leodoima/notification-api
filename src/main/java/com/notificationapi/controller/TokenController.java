package com.notificationapi.controller;

import com.notificationapi.dto.RequestSmsTokenValidateDto;
import com.notificationapi.dto.ResponseTokenDto;
import com.notificationapi.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/validate/sms")
    public ResponseEntity<ResponseTokenDto> tokenValidate(@RequestBody @Valid RequestSmsTokenValidateDto requestValidateDto) {
        var responsetokenDto = tokenService.tokenValidate(requestValidateDto);
        return ResponseEntity.status(responsetokenDto.statusCode()).body(responsetokenDto);
    }
}
