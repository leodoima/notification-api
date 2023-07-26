package com.notificationapi.service;

import br.com.totalvoice.TotalVoiceClient;
import br.com.totalvoice.api.Sms;
import com.notificationapi.model.sms.SmsModel;
import org.json.JSONObject;

public class TotalVoiceService {

    public JSONObject sendSms(SmsModel smsModel) throws Exception {
        try {
            TotalVoiceClient totalVoiceClient = new TotalVoiceClient("84f1d2eb31f60dd2733d18c15ee151d8");
            Sms sms = new Sms(totalVoiceClient);

            return sms.enviar(smsModel.getPhoneNumber(), smsModel.messageDescription());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
