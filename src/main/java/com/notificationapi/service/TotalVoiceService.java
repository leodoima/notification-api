package com.notificationapi.service;

import br.com.totalvoice.TotalVoiceClient;
import br.com.totalvoice.api.Sms;
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

    public JSONObject sendSms(SmsModel smsModel) throws Exception {
        try {
            TotalVoiceClient totalVoiceClient = new TotalVoiceClient(tokenTotalVoice);
            Sms sms = new Sms(totalVoiceClient);

            return sms.enviar(smsModel.getPhoneNumber(), smsModel.messageDescription());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
