package com.smsservice.app.sms_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Service
@Slf4j
public class SmsService {


    @Autowired
    private JavaMailSender javaMailSender;

    static String APIKEY="NjQ1MDM3NTQzNDQ4Nzc3NTM3NGYzOTUwNjc1YTM2NTc=";
    public void sendSms() {
        try {
            // Construct data
            String apiKey = "apikey=" + APIKEY;
            String message = "&message=" + "Debited balance 20000 from Account: Ac: 800235*******56455, IFSC BANK!";
            String sender = "&sender=" + "Manish Singh";
            String numbers = "&numbers=" + "918700719343";

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line).append("\n");
            }
            System.out.println(stringBuffer.toString());
            rd.close();
        } catch (ProtocolException e) {
          log.error("Protocol   Error due to : {}", e.getMessage());
        } catch (MalformedURLException e) {
            log.error("MalformedURL Error due to : {}", e.getMessage());
        } catch (UnsupportedEncodingException e) {
            log.error("Unsupported Error due to : {}", e.getMessage());
        } catch (IOException e) {
            log.error("IOException Error due to : {}", e.getMessage());
        }
        catch (Exception e)
        {
            log.error("General Error due to: {}", e.getMessage());
        }
    }

    public String sendMailService(String email) {
       try {

           String subject="Checking java";
           String text="Hello Fitness";
           SimpleMailMessage message = new SimpleMailMessage();
           message.setFrom("fitness.app.nineleaps@gmail.com");
           message.setTo(email);
           message.setSubject("OTP Message");
           message.setText("You OTP is: 2245631");
           javaMailSender.send(message);
           return "Mail sent Successfully:";

       }
       catch (Exception e)
       {
           log.error("Error: {}", e.getMessage());
       }
        return "Error";
    }
}
