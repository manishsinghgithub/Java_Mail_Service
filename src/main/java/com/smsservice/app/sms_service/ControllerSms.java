package com.smsservice.app.sms_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControllerSms {


    @Autowired
    private SmsService smsService;

    @GetMapping(value = "/g")
    public String hello()
    {
        return "Hello! It's Working : ";
    }



    @PostMapping("/send-message")
    public String sendingMessage()
    {
         smsService.sendSms();
         return "Sent";
    }


    @PostMapping("/send-mail/{email}")
    public String sendMailTo(@PathVariable String email)
    {
        return smsService.sendMailService(email);
    }


}
