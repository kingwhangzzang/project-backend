package com.king.projectbackend.controller;

import com.king.projectbackend.entity.Member;
import com.king.projectbackend.prop.JwtTokenProvider;
import com.king.projectbackend.service.CustomUserDetailService;
import com.king.projectbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomUserDetailService customUserDetailService;

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/api/signup")
    public ResponseEntity<String> signup(@RequestBody Member member){
        log.info(member.getUsername());
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
    //로그인 정보 다식 구현
    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody Member member) {
       Member result = memberService.login(member.getMemberLoginId());
       if(result != null){
           if (passwordEncoder.matches(member.getMemberPassword(), result.getMemberPassword())) {
               System.out.println("오류찾기1"+result.getMemberIdx());
               // 3. UserDetails 로드
               UserDetails userDetails = customUserDetailService.loadUserByUsername(member.getMemberLoginId());

               // 4. Authentication 객체 생성
               UsernamePasswordAuthenticationToken authenticationToken =
                       new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

               // 5. SecurityContext에 인증 설정
               SecurityContextHolder.getContext().setAuthentication(authenticationToken);

               // 6. JWT 토큰 생성 //이부분은 clams 설정을 재설정 해야함 header에 정보를 넘겨야함
               String token = jwtTokenProvider.createJWT(result,"ROLE_USER");
               System.out.println(token);
               HttpHeaders headers = new HttpHeaders();
               headers.add("Authorization", "Bearer " + token);
               // 7. JWT 응답 반환
               return ResponseEntity.ok().headers(headers).body("로그인 성공");
           }
       }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
    }
}
