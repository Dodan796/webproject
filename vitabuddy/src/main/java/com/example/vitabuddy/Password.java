package com.example.vitabuddy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {

    public static void main(String[] args) {
        System.out.println("프로그램 시작");

        String rawPassword = "1345";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("원본 비밀번호: " + 11111111);
        System.out.println("암호화된 비밀번호: " + encodedPassword);

    }
}