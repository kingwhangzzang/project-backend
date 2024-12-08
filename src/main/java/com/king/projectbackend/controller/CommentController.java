package com.king.projectbackend.controller;

import com.king.projectbackend.entity.Comment;
import com.king.projectbackend.entity.CommentTag;
import com.king.projectbackend.entity.Tag;
import com.king.projectbackend.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {
    private CommentService commentService;
    @PostMapping("/api/write/save")
    public ResponseEntity<String> addComment(@RequestBody Comment comment) {
        System.out.println(comment.getCommentTags().toString());
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println("사용자 정보144:"+SecurityContextHolder.getContext().getAuthentication());
        System.out.println("사용자 정보2:"+context);
        return ResponseEntity.ok("success");
    }
}
