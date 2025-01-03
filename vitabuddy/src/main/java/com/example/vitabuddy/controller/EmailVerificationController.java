package com.example.vitabuddy.controller;

import com.example.vitabuddy.service.EmailVerificationService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
@RequiredArgsConstructor
public class EmailVerificationController {

    // 주입
    private final EmailVerificationService emailVerificationService;

    // 이메일 보내는 로직.
    @RequestMapping("/member/emailverify")
    @ResponseBody
    public String emailVerification(@RequestParam("email") String email) throws MessagingException, UnsupportedEncodingException {
        String code = emailVerificationService.sendSimpleMessage(email);
        System.out.println("이메일인증 코드입니다" + code);
        return code;
    }

}
