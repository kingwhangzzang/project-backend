package com.king.projectbackend.controller;

import com.king.projectbackend.entity.Comment;
import com.king.projectbackend.entity.CommentTag;
import com.king.projectbackend.entity.Tag;
import com.king.projectbackend.prop.JwtTokenProvider;
import com.king.projectbackend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CommentController {
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/api/write/save")
    public ResponseEntity<String> addComment(@RequestHeader("Authorization") String token, @RequestBody Comment comment) {
        System.out.println(comment.getCommentTags().toString());
        System.out.println("token: " + token);
        jwtTokenProvider.validateToken(token);
        Long memberIdx = jwtTokenProvider.getUsernameFromToken(token);
        System.out.println(memberIdx);
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println("사용자 정보144:"+SecurityContextHolder.getContext().getAuthentication());
        System.out.println("사용자 정보2:"+context);
        return ResponseEntity.ok("success");
    }
}
