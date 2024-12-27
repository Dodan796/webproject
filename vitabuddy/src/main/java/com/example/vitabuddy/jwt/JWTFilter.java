package com.example.vitabuddy.jwt;

import com.example.vitabuddy.dto.MemberDTO;
import com.example.vitabuddy.dto.UserInfo;
import com.example.vitabuddy.service.RefreshService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final RefreshService refreshService;

    public JWTFilter(JWTUtil jwtUtil, RefreshService refreshService) {
        this.jwtUtil = jwtUtil;
        this.refreshService = refreshService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String accessToken = request.getHeader("access");
        String refreshToken = null;

        // Refresh 토큰 쿠키에서 가져오기
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refresh".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                }
            }
        }

        try {
            // Access 토큰 유효성 검증
            if (accessToken != null) {
                jwtUtil.isExpired(accessToken);
                // 인증 로직 처리
                String userId = jwtUtil.getUserId(accessToken);
                String userRole = jwtUtil.getUserRole(accessToken);
                setAuthentication(userId, userRole);
                filterChain.doFilter(request, response);
                return;
            }

            // Access 토큰이 만료된 경우 Refresh 토큰으로 재발급
            if (refreshToken != null) {
                jwtUtil.isExpired(refreshToken); // Refresh 토큰 유효성 검증
                if (!refreshService.existsByRefresh(refreshToken)) {
                    throw new IllegalArgumentException("Refresh token invalid");
                }

                String userId = jwtUtil.getUserId(refreshToken);
                String userRole = jwtUtil.getUserRole(refreshToken);

                // 새 Access 토큰 발급
                String newAccessToken = jwtUtil.createJwt("access", userId, userRole, 600000L);
                response.setHeader("access", newAccessToken);
                setAuthentication(userId, userRole);
                filterChain.doFilter(request, response);
                return;
            }

            // Access 및 Refresh 토큰 모두 없는 경우
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | IllegalArgumentException e) {
            // Refresh 토큰 만료 시 로그아웃 처리
            if ("refresh".equals(jwtUtil.getCategory(refreshToken))) {
                logout(response);
            }
        }
    }

    private void setAuthentication(String userId, String userRole) {
        MemberDTO dto = new MemberDTO();
        dto.setUserId(userId);
        dto.setUserRole(userRole);

        UserInfo userInfo = new UserInfo(dto);
        Authentication authToken = new UsernamePasswordAuthenticationToken(userInfo, null, userInfo.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("refresh", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}

