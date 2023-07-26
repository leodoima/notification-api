package com.notificationapi.service;

import br.com.totalvoice.TotalVoiceClient;
import br.com.totalvoice.api.Sms;
import com.notificationapi.dto.ResponseSmsDto;
import com.notificationapi.enums.StatusSendNotification;
import com.notificationapi.model.sms.SmsModel;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TotalVoiceService {

    private static String tokenTotalVoice;

    @Value("${api.total.voice.token}")
    private void setTokenTotalVoice(String myValue) {
        this.tokenTotalVoice = myValue;
    }

    public ResponseSmsDto sendSms(SmsModel smsModel) {
        ResponseSmsDto responseSmsDto = null;

        try {
            TotalVoiceClient totalVoiceClient = new TotalVoiceClient(tokenTotalVoice);
            Sms sms = new Sms(totalVoiceClient);

            JSONObject result = sms.enviar(smsModel.getPhoneNumber(), smsModel.messageDescription());
            System.out.println(result);
            responseSmsDto = convertReturnSms(result);
            System.out.println(responseSmsDto);

        } catch (Exception ex) {
            new Exception(ex.getMessage());
        } finally {
            setSendStatus(responseSmsDto, smsModel);
            System.out.println(smsModel);
            // salvar smsModel
        }
        return responseSmsDto;
    }

    private ResponseSmsDto convertReturnSms(JSONObject jsonObject) {
        return new ResponseSmsDto((String) jsonObject.get("mensagem"), (Integer) jsonObject.get("status"));
    }

    private SmsModel setSendStatus(ResponseSmsDto responseSmsDto, SmsModel smsModel) {
        switch (responseSmsDto.statusCode()) {
            case 200:
                smsModel.setStatusSendNotification(StatusSendNotification.SUCCESS);
                return smsModel;
            default:
                smsModel.setStatusSendNotification(StatusSendNotification.ERROR);
                return smsModel;
        }
    }
}
