package com.king.projectbackend.controller;

import com.king.projectbackend.entity.Member;
import com.king.projectbackend.prop.JwtProps;
import com.king.projectbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProps jwtProps;

    @PostMapping("/api/signup")
    public ResponseEntity<String> signup(@RequestBody Member member){
        System.out.println(member);
        String beforePassword = passwordEncoder.encode(member.getMemberPassword());
        member.setMemberPassword(beforePassword); //암호화된 값 넣어서 수정

        boolean result = memberService.join(member);
        if(result){
            return ResponseEntity.ok("회원가입 성공");
        }
        else {
            return ResponseEntity.badRequest().body("회원가입 실패");
        }
    }
    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody Member member){
        System.out.println("member = " + member);
        Member result = memberService.login(member.getMemberLoginId());
        if(result != null){
            if(passwordEncoder.matches(member.getMemberPassword(), result.getMemberPassword())){
                String token=jwtProps.createToken(result,"USER_ROLES");
                Map<String,String> response = new HashMap<>();
                response.put("Authorization","Bearer "+token);
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization","Bearer "+token);
                System.out.println(token);
                return ResponseEntity.ok().headers(headers).body("완료"); //이 값이 헤더 안넘겨져
            }
            log.info("아이디 틀림");
            return ResponseEntity.status(400).body("로그인 실패");
        }
        return ResponseEntity.status(400).body("로그인 실패");
    }
}
