package com.notificationapi.service;

import br.com.totalvoice.TotalVoiceClient;
import com.notificationapi.dto.ResponseSmsDto;
import com.notificationapi.enums.SmsStatusSendEnum;
import com.notificationapi.model.Sms;
import com.notificationapi.repository.SmsRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TotalVoiceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TotalVoiceService.class);

    @Autowired
    private SmsRepository smsRepository;

    @Value("${api.total.voice.token}")
    private String tokenTotalVoice;

    public ResponseSmsDto sendSms(Sms smsModel) {

        LOGGER.info("Initializing message sending execution to {} to number {}", smsModel.getSmsTypeEnum(), smsModel.getPhoneNumber());

        ResponseSmsDto responseSmsDto = null;

        try {
            TotalVoiceClient totalVoiceClient = new TotalVoiceClient(tokenTotalVoice);
            br.com.totalvoice.api.Sms smsTotalVoice = new br.com.totalvoice.api.Sms(totalVoiceClient);

            JSONObject result = smsTotalVoice.enviar(smsModel.getPhoneNumber(), smsModel.getMessageContent());
            responseSmsDto = convertReturnSms(result);
            
            LOGGER.info("Result after processed TotalVoice.enviar(): {}", result);

        } catch (Exception ex) {
            smsModel.setSmsStatusSendEnum(SmsStatusSendEnum.ERROR);
            LOGGER.info("Exception error to operate a TotalVoice. {}", ex);
        } finally {

            if (smsModel.getSmsStatusSendEnum().equals(SmsStatusSendEnum.REQUEST)) {
                setSendStatus(responseSmsDto, smsModel);
            }

            if (responseSmsDto == null) {
                responseSmsDto = new ResponseSmsDto("Internal error to send a sms", 500);
            }

            smsRepository.save(smsModel);

            LOGGER.info("Finaly processed sms for TotalVoice");

            return responseSmsDto;
        }
    }

    private ResponseSmsDto convertReturnSms(JSONObject jsonObject) {
        return new ResponseSmsDto((String) jsonObject.get("mensagem"), (Integer) jsonObject.get("status"));
    }

    private Sms setSendStatus(ResponseSmsDto responseSmsDto, Sms sms) {
        switch (responseSmsDto.statusCode()) {
            case 200:
                sms.setSmsStatusSendEnum(SmsStatusSendEnum.SUCCESS);
                return sms;
            default:
                sms.setSmsStatusSendEnum(SmsStatusSendEnum.ERROR);
                return sms;
        }
    }
}
