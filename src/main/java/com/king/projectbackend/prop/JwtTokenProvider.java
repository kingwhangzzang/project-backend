package com.king.projectbackend.prop;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProps jwtProps;


    // JWT 토큰 생성
    public String createToken(String username) {
        // 현재 시간을 기반으로 만료 시간을 설정
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtProps.getExpiration() * 1000);

        return Jwts.builder()
                .setSubject(username)  // 사용자 정보
                .setIssuedAt(now)  // 발급 시간
                .setExpiration(expiration)  // 만료 시간
                .signWith(SignatureAlgorithm.HS512, jwtProps.getSecurityKey())  // 서명
                .compact();
    }

    // JWT 토큰에서 사용자 이름을 추출
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProps.getSecurityKey())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();  // 사용자 이름 반환
    }

    // JWT 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProps.getSecurityKey())
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;  // 유효하지 않은 토큰
        }
    }
}
