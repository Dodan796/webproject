package com.example.vitabuddy.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vitabuddy.dao.MemberDAO;

@Service
public class LoginService implements ILoginService {

    @Autowired
    private MemberDAO memberDAO;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public String login(HashMap<String, Object> map) {
        String userId = (String) map.get("id");
        String rawPassword = (String) map.get("pwd");

        // DB에서 암호화된 비밀번호를 가져옴
        String encryptedPassword = memberDAO.getPasswordByUserId(userId);
        
        String result = "fail";
        
        // 암호화된 비밀번호와 사용자 입력 비밀번호 비교
        if (encryptedPassword != null && passwordEncoder.matches(rawPassword, encryptedPassword)) {
            result = "success";
        }
        return result;
    }
}
