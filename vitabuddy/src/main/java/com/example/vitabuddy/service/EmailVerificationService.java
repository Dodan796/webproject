package com.example.vitabuddy.service;


import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor //=> 생성자를 final로 선언시 초기화를 자동으로 진행해주는 어노테이션.
public class EmailVerificationService implements IEmailVerificationService {

    private final JavaMailSender javaGmailSender;
    private String code;


    @Override
    public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException {
        MimeMessage gmessage = javaGmailSender.createMimeMessage();
        gmessage.addRecipients(Message.RecipientType.TO, to);  //to : 보내는 대상
        gmessage.setSubject("회원가입 인증번호입니다.");


        //template.html

        //gmessage.setText(template, "utf-8", "html");
        gmessage.setFrom(new InternetAddress("noreply2583@gmail.com", "vitabuddy"));

        return gmessage;
    }

    @Override
    public String sendSimpleMessage(String to) throws MessagingException, UnsupportedEncodingException {

        code = createCode();
        MimeMessage message = createMessage(to);

        try {
            javaGmailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
        return code;
    }

    @Override
    public String createCode() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤, rnd 값에 따라서 아래 switch 문이 실행됨

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    // a~z (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    // A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }
}
