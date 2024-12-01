package com.king.projectbackend.controller;

import com.king.projectbackend.entity.Member;
import com.king.projectbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final MemberService memberService;
    private final BCryptPasswordEncoder passwordEncoder;

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
}
