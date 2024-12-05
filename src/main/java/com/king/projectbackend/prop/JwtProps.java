package com.king.projectbackend.prop;

import com.king.projectbackend.entity.Member;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProps {
    @Value("${jwt.token.securityKey}")
    private String securityKey;
    private SecretKey key;
    public String createToken(Member member,String role) {
        Map<String, Object> claims = new HashMap<>();
        byte[] keyBytes = securityKey.getBytes(); //securityKey byte로 변환
        Date now = new Date();
        SecretKey key = new SecretKeySpec(keyBytes,0,keyBytes.length,"HmacSHA512");

        return Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더
                .setIssuer("fresh") //발급자
                .setIssuedAt(now)
                .claim("memberLoginId",member.getMemberLoginId()) // 페이로드==정보
                .claim("memberName",member.getMemberName())
                .claim("memberNickname",member.getMemberNickname())
                .claim("black",member.getBlacklists())
                .claim("roles",role)
                .signWith(SignatureAlgorithm.HS512,key) //서명 부분
                .compact();
    }
}
