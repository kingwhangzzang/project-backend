package com.king.projectbackend.config;


import com.king.projectbackend.prop.JwtProps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(basic -> basic.disable())
                .formLogin(form -> form.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/signup", "/api/login", "/api/signup","/api/board/**","/api/main","/main").permitAll()
                        .anyRequest().authenticated());

        http.sessionManagement((session)->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
    //비밀번호 암호화를 빈으로 등록함
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}