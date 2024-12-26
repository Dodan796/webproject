package com.example.vitabuddy.config;

import com.example.vitabuddy.jwt.JWTFilter;
import com.example.vitabuddy.jwt.JWTUtil;
import com.example.vitabuddy.jwt.LoginFilter;
import com.example.vitabuddy.jwt.CustomLogoutFilter;
import com.example.vitabuddy.service.RefreshService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Slf4j
@Configuration
@EnableWebSecurity
public class webSecurityConfig {

    //JWT 토큰 주입
    private final JWTUtil jwtUtil;

    //AuthenticationManager가 인자로 받을 AuthenticationConfiguration 객체 생성자를 주입해준다.
    private final AuthenticationConfiguration authenticationConfiguration;

    //RefreshService 주입
    private final RefreshService refreshService;

    public webSecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil, RefreshService refreshService) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
        this.refreshService = refreshService;
    }

    //비밀번호 암호화
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //AuthenticationManager Bean을 등록한다
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //1. csrf disable
        http.csrf((auth) -> auth.disable());

        //2. Form 로그인 방식 disable
        http.formLogin((auth) -> auth.disable());

        //3. 경로별 인가 작업
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/**","/login").permitAll() //=>
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/reissue").permitAll()
                .anyRequest().authenticated());

        //4. 세션설정
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //5. 필터추가

        // 5-1
        http
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

        // 5-2LoginFilter()는 인자를 받아서 (AuthenticationManager() 메소드에 authenticationConfiguration 객체를 넣어야 함) 따라서 등록 필요
        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, refreshService), UsernamePasswordAuthenticationFilter.class);

        //6. 로그아웃필터 추가
        http
               .addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshService), LogoutFilter.class);

        return http.build();
    }
}