package com.example.vitabuddy.jwt;

import com.example.vitabuddy.model.RefreshVO;
import com.example.vitabuddy.service.RefreshService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    // AuthenticationManager 필드선언
    private final AuthenticationManager authenticationManager;

    // JWT token 주입
    private final JWTUtil jwtUtil;

    // Refresh VO & RefreshService 필드선언
    private RefreshService refreshService;

    // LoginFilter에 주입
    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, RefreshService refreshService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.refreshService = refreshService;
    }

    // cookie 생성 메서드
    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true);
        //cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }

    // Refresh 메서드 추가
    private void addRefresh(String userEmail, String refreshToken, Long expiration){
        Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis() + expiration);

        RefreshVO refreshVO = new RefreshVO();
        refreshVO.setUserEmail(userEmail);
        refreshVO.setRefreshToken(refreshToken);
        refreshVO.setExpiration(timestamp);

        // MyBatis를 통해 데이터베이스에 저장
        refreshService.saveRefreshToken(refreshVO);
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
        // 세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // 회원 정보
        String userEmail = authentication.getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String userRole = auth.getAuthority();

        // 토큰 생성
        String access = jwtUtil.createJwt("access", userEmail, userRole, 600000L);
        String refresh = jwtUtil.createJwt("refresh", userEmail, userRole, 86400000L);

        // Refresh 토큰 저장 로직
        addRefresh(userEmail, refresh, 86400000L);

        // 응답 설정
        response.setHeader("access", access);
        response.addCookie(createCookie("refresh", refresh));

        // userRole과 userEmail를 쿠키에 저장
        response.addCookie(createCookie("userRole", userRole));
        response.addCookie(createCookie("userEmail", userEmail));

        response.setStatus(HttpStatus.OK.value());
    }

    // 로그인 실패 시 실행하는 메서드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(401);
    }
}