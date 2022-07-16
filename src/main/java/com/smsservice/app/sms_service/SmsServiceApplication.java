package com.smsservice.app.sms_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication (exclude = {ErrorMvcAutoConfiguration.class})
public class SmsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmsServiceApplication.class, args);
	}

}
