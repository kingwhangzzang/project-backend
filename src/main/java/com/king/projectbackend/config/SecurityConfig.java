package com.king.projectbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(basic -> basic.disable()) // Basic 인증 비활성화
                .formLogin(form -> form.disable()) // 기본 로그인 폼 비활성화
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .cors(withDefaults()) // CORS 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/signup", "/api/login", "/api/signup").permitAll() // 특정 경로에 대한 접근 허용
                        .anyRequest().authenticated()); // 나머지 경로는 인증 필요

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화 방식으로 BCryptPasswordEncoder 사용
    }
}