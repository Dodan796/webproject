package com.example.vitabuddy.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;

public interface IEmailVerificationService {

    // 1. 메일에 해당하는 내용을 작성하는 코드
    MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException;

    // 2. 메일을 보내기 위한 메서드
    String sendSimpleMessage(String to) throws MessagingException, UnsupportedEncodingException;

    // 3. 랜덤 인증번호 생성 메서드.
    public String createCode();
}
