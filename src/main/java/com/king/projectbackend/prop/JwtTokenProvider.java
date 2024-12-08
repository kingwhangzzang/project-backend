package com.king.projectbackend.prop;

import com.king.projectbackend.entity.Member;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    private final JwtProps jwtProps;

    public JwtTokenProvider(JwtProps jwtProps) {
        this.jwtProps = jwtProps;
    }

    // JWT 토큰 생성
    public String createJWT(Member member, String roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles); // 권한추가(권한에 따른 페이지 로딩)
        Date now = new Date();

        // application.yml에서 읽은 securityKey를 사용하여 SecretKey 생성
        byte[] keyBytes = jwtProps.getSecurityKey().getBytes();
        SecretKey key = new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA512");

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("fresh") // 발급자 정보
                .setIssuedAt(now) // 발급 시간
                .claim("memberIdx",member.getMemberIdx())
                .claim("userId", member.getMemberLoginId())
                .claim("name", member.getMemberName())
                .claim("blackStatus", member.getBlacklists())
                .claim("roles", roles)
                .signWith(key, SignatureAlgorithm.HS512) // 서명 알고리즘과 시크릿 키
                .compact(); // 토큰 생성
    }

    // JWT 토큰에서 사용자 이름을 추출
    public Long getUsernameFromToken(String token) {
        String cleanToken = token.replace("Bearer ", "");
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProps.getSecurityKey().getBytes()) // 시크릿 키 바이트 배열 사용
                .parseClaimsJws(cleanToken)
                .getBody();
        Long memberIdx = Long.parseLong(claims.get("memberIdx").toString());
        return memberIdx;
    }

    // JWT 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProps.getSecurityKey().getBytes()) // 시크릿 키 바이트 배열 사용
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;  // 유효하지 않은 토큰
        }
    }
}