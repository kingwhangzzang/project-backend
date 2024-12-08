package com.king.projectbackend.prop;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Getter
public class JwtProps {
    @Value("${jwt.token.key}") //application.yml Jwt.token.key값을 가져옴
    private String securityKey;

    @Value("${jwt.token.expiration}") //application.yml 만료 시간을 가져옴
    private Long expiration;
}
