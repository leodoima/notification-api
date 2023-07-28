package com.notificationapi.service;

import br.com.totalvoice.TotalVoiceClient;
import com.notificationapi.dto.ResponseSmsDto;
import com.notificationapi.enums.StatusSendNotification;
import com.notificationapi.model.Sms;
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

    public ResponseSmsDto sendSms(Sms smsModel) {
        ResponseSmsDto responseSmsDto = null;

        try {
            TotalVoiceClient totalVoiceClient = new TotalVoiceClient(tokenTotalVoice);
            br.com.totalvoice.api.Sms sms = new br.com.totalvoice.api.Sms(totalVoiceClient);

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

    private Sms setSendStatus(ResponseSmsDto responseSmsDto, Sms sms) {
        switch (responseSmsDto.statusCode()) {
            case 200:
                sms.setStatusSendNotification(StatusSendNotification.SUCCESS);
                return sms;
            default:
                sms.setStatusSendNotification(StatusSendNotification.ERROR);
                return sms;
        }
    }
}
