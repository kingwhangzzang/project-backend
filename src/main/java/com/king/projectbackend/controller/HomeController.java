package com.king.projectbackend.controller;

import com.king.projectbackend.prop.JwtTokenProvider;
import com.king.projectbackend.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final TagService tagService;
    private final JwtTokenProvider jwtTokenProvider;
    //기본적으로 데이터를 wirte에 태그형식으로 쏠거임
//    @PostMapping("/api/write/home")
//    public ResponseEntity<List<Tag>> login() {
//        List<Tag> tags = tagService.getTags();
//        System.out.println("태그값"+tags.size());
//        return  ResponseEntity.ok(tags);
//    }
//    @GetMapping("/api/home")
//    public ResponseEntity<List<Comment>> homeComment(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ){
//
//    }
}
