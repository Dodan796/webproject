package com.example.vitabuddy.jwt;

import com.example.vitabuddy.dto.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    // AuthenticationManager 필드선언
    private final AuthenticationManager authenticationManager;

    // JWT token 주입
    private final JWTUtil jwtUtil;

    // LoginFilter에 주입
    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // 요청의 InputStream에서 JSON 데이터를 읽음
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> credentials = mapper.readValue(request.getInputStream(), Map.class);

            String username = credentials.get("username");
            String password = credentials.get("password");

            System.out.println("username = " + username); // 디버깅용
            System.out.println("password = " + password); // 디버깅용

            // UsernamePasswordAuthenticationToken 생성
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

            // AuthenticationManager로 인증 위임
            return authenticationManager.authenticate(authToken);

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse authentication request body", e);
        }
    }

    // 로그인 성공 시 실행하는 메서드 (JWT를 발급하는 곳)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {

        UserInfo info = (UserInfo) authentication.getPrincipal();

        String userId = info.getUsername();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String userRole = auth.getAuthority();
        String token = jwtUtil.createJwt(userId, userRole, 60 * 60 * 24L);

        response.addHeader("Authorization", "Bearer " + token);
    }

    // 로그인 실패 시 실행하는 메서드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(401);
    }
}
