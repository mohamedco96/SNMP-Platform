package com.iti.snmp.action;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author asalah
 */
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import java.io.IOException;

public class nxSms {

    public static void sendSMS(String phone, String messageText) throws IOException, NexmoClientException {
        NexmoClient client = new NexmoClient.Builder()
                .apiKey("e5425335")
                .apiSecret("jsrIVVdLHDADF79a")
                .build();

        TextMessage message = new TextMessage("Vonage SMS API", phone, messageText);

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
            System.out.println(responseMessage);
        }
    }

}
