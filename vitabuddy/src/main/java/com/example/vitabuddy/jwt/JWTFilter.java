package com.example.vitabuddy.jwt;

import com.example.vitabuddy.dto.MemberDTO;
import com.example.vitabuddy.dto.UserInfo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //1. request에서 Authorization 헤더를 찾음
        String authorization = request.getHeader("Authorization");

        //Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {

            System.out.println("token null");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        String token = authorization.split(" ")[1];

        //토큰 소멸 시간 검증
        if(jwtUtil.isExpired(token)){
            System.out.println("token expired");
            filterChain.doFilter(request,response);

            return;
        }

        //token에서 userId, userRole를 가져온다
        String userId = jwtUtil.getUserId(token);
        String userRole = jwtUtil.getUserRole(token);

        //MemberDTO를 생성하여 값을 set
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId(userId);
        memberDTO.setUserPwd("temppassword");
        memberDTO.setUserRole(userRole);

        //UserInfo에 회원정보 객체를 담는다.
        UserInfo userInfo = new UserInfo(memberDTO);

        //Spring Security 인증 토큰을 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(userInfo, null, userInfo.getAuthorities());

        //session에 사용자를 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request,response);

    }
}