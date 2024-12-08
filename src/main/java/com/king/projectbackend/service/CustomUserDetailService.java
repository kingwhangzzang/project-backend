package com.king.projectbackend.service;

import com.king.projectbackend.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberService memberService;

    //SecurityContextHolder 서버에 사용자 저장함
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 이미 서버에 저장하는 로직을 구현했음
        Member member = memberService.login(username);

        return User.builder()
                .username(member.getMemberLoginId())
                .password(member.getMemberPassword())
                .authorities(member.getAuthorities())
                .build();
    }
}
