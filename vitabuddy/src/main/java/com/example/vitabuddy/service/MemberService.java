package com.example.vitabuddy.service;

import com.example.vitabuddy.dto.KakaoDTO;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vitabuddy.dao.MemberDAO;
import com.example.vitabuddy.dto.MemberDTO;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

@Service
public class MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private PasswordEncoder passwordEncoder; // 비밀번호 암호화를 위한 PasswordEncoder 빈 주입

    @Transactional
    public boolean registerMember(MemberDTO member) {
        // 비밀번호 암호화하여 설정
        String encryptedPassword = passwordEncoder.encode(member.getUserPwd());
        member.setUserPwd(encryptedPassword);
        memberDAO.insertMember(member);
        return true;  // 회원가입 성공
    }

    public boolean isUserIdAvailable(String userId) {
        return memberDAO.getUserById(userId) == null; // 아이디가 존재하지 않으면 true 반환
    }

    public boolean isUserEmailAvailable(String userEmail) {
        return memberDAO.getUserByEmail(userEmail) == null;
    }

    // getUserInfo 메서드 추가
    public MemberDTO getUserInfo(String userId) {
        return memberDAO.getUserById(userId); // MemberDAO에서 회원 정보 가져오기
    }




}
