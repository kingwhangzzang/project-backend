package com.king.projectbackend.controller;

import com.king.projectbackend.entity.Comment;
import com.king.projectbackend.entity.Member;
import com.king.projectbackend.entity.Tag;
import com.king.projectbackend.service.MemberService;
import com.king.projectbackend.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final TagService tagService;
    //기본적으로 데이터를 wirte에 태그형식으로 쏠거임
    @PostMapping("/api/write/home")
    public ResponseEntity<List<Tag>> login() {
        List<Tag> tags = tagService.getTags();
        System.out.println("태그값"+tags.size());
        return  ResponseEntity.ok(tags);
    }
}
