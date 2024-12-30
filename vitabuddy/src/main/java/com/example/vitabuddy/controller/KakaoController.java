package com.example.vitabuddy.controller;


import com.example.vitabuddy.dto.KakaoDTO;
import com.example.vitabuddy.service.KakaoService;
import com.example.vitabuddy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;

@Controller
public class KakaoController {

    @Autowired
    KakaoService kakaoService;

    //카카오 로그인
    //jsp(프론트)에서 인가 코드 요청 -> 로그인(동의버튼클릭) 이후, url보면 code가 포함되어있는 링크가 보인다
    //여기서 callback url로 code를 받아와서 아래 로직 작성.
    @RequestMapping("/oauth/kakao/callback")
    public String kakaoLogin(@RequestParam("code") String code) throws IOException {

        //액세스 토큰 요청
        System.out.println("인가코드는 : " + code + " 입니다.");  //인가 코드 로그 출력

        String access_token =  kakaoService.getAccessToken(code);  //카카오 서버에서 받은 "인가코드"를 서비스계층으로 보낸 후, 토큰을 받는다
        System.out.println("토큰은 : " + access_token + " 입니다." );


        //사용자 정보 가져오기 요청
        HashMap<String, Object> userInfo = kakaoService.getkakaoUserInfo(access_token);

        long userId = (long) userInfo.get("userId");

        System.out.println("사용자 정보는 : " + userInfo);  //로그 출력 확인
        String userName = (String) userInfo.get("userName");
        System.out.println("사용자 이름은 : " + userName);

        String userEmail = (String) userInfo.get("userEmail");
        System.out.println("사용자 이메일은 : " + userEmail);


        //가져온 정보를 MemberDTO에 데이터 저장 - 현재는 userName(nickname), userEmail 데이터만 추출할 수 있다
        KakaoDTO kakaoDTO = new KakaoDTO();
        kakaoDTO.setUserId(userId);
        kakaoDTO.setUserName(userName);
        kakaoDTO.setUserEmail(userEmail);

        System.out.println("카카오 로그인 임시 id 등록 : " + kakaoDTO.getUserId());
        System.out.println("카카오 로그인 이메일 등록 : " + kakaoDTO.getUserEmail());
        System.out.println("카카오 로그인 이름 등록 : " + kakaoDTO.getUserName());  //왜 MemberDTO 로 했을 대는 null이 나왔는지..?

        //등록하기
        kakaoService.registerkakaoMember(kakaoDTO);

        return "success";  //(임시) 페이지로 이동

    }



}
