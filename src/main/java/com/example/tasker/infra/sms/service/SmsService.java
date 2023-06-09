package com.example.tasker.infra.sms.service;

import com.example.tasker.infra.sms.dto.SmsSendRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface SmsService {

    String sendSms(SmsSendRequest smsSendRequest) throws JsonProcessingException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException;


}
