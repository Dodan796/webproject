package com.example.vitabuddy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class emailConfig {

    @Value("${spring.mail.username}") private String username;
    @Value("${spring.mail.password}") private String password;

    @Bean
    public JavaMailSender javaGmailSender(){
        JavaMailSenderImpl javaGmailSender = new JavaMailSenderImpl();
        javaGmailSender.setHost("smtp.gmail.com");
        javaGmailSender.setPort(587);
        javaGmailSender.setUsername(username);
        javaGmailSender.setPassword(password);

        Properties Gproperties = new Properties();
        Gproperties.put("mail.transport.protocol","smtp");
        Gproperties.put("mail.smtp.auth","true");
        Gproperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Gproperties.put("mail.smtp.starttls.enable", "ture");
        Gproperties.put("mail.debug", "true");
        Gproperties.put("mail.smtp.ssl.trust","smtp.gmail.com");
        Gproperties.put("mail.smtp.ssl.protocols","TLSv1.3");

        javaGmailSender.setJavaMailProperties(Gproperties);

        return javaGmailSender;

    }
}
