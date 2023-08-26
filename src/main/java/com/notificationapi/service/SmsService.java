package com.notificationapi.service;

import br.com.totalvoice.TotalVoiceClient;
import com.notificationapi.dto.RequestSmsTokenDto;
import com.notificationapi.enums.StatusSendEnum;
import com.notificationapi.builder.SmsBuilder;
import com.notificationapi.model.Sms;
import com.notificationapi.repository.SmsRepository;;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
;

@Service
public class SmsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsService.class);

    @Value("${api.total.voice.token}")
    private String secretKeyTotalVoice;

    @Autowired
    private SmsRepository smsRepository;

    @Autowired
    private SmsBuilder smsBuilder;


    public ResponseEntity<String> requestToken(RequestSmsTokenDto request) {
        var smsRequest = generateToken(request);

        smsRequest.setStatusSendEnum(sendToken(smsRequest));
        smsRepository.save(smsRequest);

        LOGGER.info("Finaly processed sms for TotalVoice");

        if (smsRequest.getStatusSendEnum().equals(StatusSendEnum.SUCCESS)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    public Sms generateToken(RequestSmsTokenDto request) {
        return smsBuilder.smsToken(request);
    }

    public StatusSendEnum sendToken(Sms smsModel) {

        LOGGER.info("Initializing message sending execution to {} to number {}", smsModel.getTokenTypeEnum(), smsModel.getPhoneNumber());

        JSONObject result = null;

        try {
            TotalVoiceClient totalVoiceClient = new TotalVoiceClient(secretKeyTotalVoice);
            br.com.totalvoice.api.Sms smsTotalVoice = new br.com.totalvoice.api.Sms(totalVoiceClient);

            result = smsTotalVoice.enviar(smsModel.getPhoneNumber(), smsModel.getMessageContent());
            LOGGER.info("Result after processed TotalVoice.enviar(): {}", result);

        } catch (Exception ex) {
            LOGGER.info("Exception error to operate a TotalVoice. {}", ex.getMessage());
        }

        return validStatusSend(result);
    }

    private StatusSendEnum validStatusSend(JSONObject resultSend) {

        if (resultSend == null) {
            return StatusSendEnum.ERROR;
        }

        if ((Integer) resultSend.get("status") == 200) {
            return StatusSendEnum.SUCCESS;
        }

        return StatusSendEnum.ERROR;
    }
}
