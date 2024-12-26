package com.example.vitabuddy.jwt;

import com.example.vitabuddy.dto.MemberDTO;
import com.example.vitabuddy.dto.UserInfo;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //1. Header에서 access키에 담긴 token을 꺼낸다
        String accessToken = request.getHeader("access");

        //2. token이 없으면 다음 필터로 넘긴다.
        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        //3. 토큰의 만료 여부를 확인, 만료시 다음 필터로 넘기지 않는다
        try {
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {

            //response body
            PrintWriter writer = response.getWriter();
            writer.print("access token expired");

            //response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //4. 토큰이 access인지 확인(발급시 Payload에 명시한다)
        String category = jwtUtil.getCategory(accessToken);
        if (!category.equals("access")) {
            //response body
            PrintWriter writer = response.getWriter();
            writer.print("invalid access token");

            //response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //5. username, userRole 값을 획득
        String username = jwtUtil.getUserId(accessToken);
        String userRole = jwtUtil.getUserRole(accessToken);

        MemberDTO dto = new MemberDTO();
        dto.setUserId(username);
        dto.setUserRole(userRole);

        UserInfo userInfo = new UserInfo(dto);

        Authentication authToken = new UsernamePasswordAuthenticationToken(userInfo, null, userInfo.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);


    }
}