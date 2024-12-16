package com.king.projectbackend.controller;

import com.king.projectbackend.entity.Board;
import com.king.projectbackend.prop.JwtTokenProvider;
import com.king.projectbackend.service.BoardService;
import com.king.projectbackend.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {
    private final BoardService boardService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/api/main")
    public ResponseEntity<List<Board>> home(@RequestParam(value = "disclosure",defaultValue="공개") String disclosure) {
        System.out.println("데이터를 가져옴");
        List<Board> boards = boardService.boardFindByDisClosure(disclosure);
        System.out.println(boards.size());
        return ResponseEntity.ok().body(boards); // 받아온 데이터 전달
    }

}
