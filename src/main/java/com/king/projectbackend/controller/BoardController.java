package com.king.projectbackend.controller;

import com.king.projectbackend.entity.Board;
import com.king.projectbackend.entity.Member;
import com.king.projectbackend.entity.TagMapping;
import com.king.projectbackend.prop.JwtTokenProvider;
import com.king.projectbackend.service.BoardService;
import com.king.projectbackend.service.CommentService;
import com.king.projectbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class BoardController {
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;
    private final BoardService boardService;

    @PostMapping("/api/board/save")
    public ResponseEntity<String> addComment(@RequestHeader("Authorization") String token, @RequestBody Board board) {
        System.out.println("token: " + token);
        jwtTokenProvider.validateToken(token);
        Long memberIdx = jwtTokenProvider.getUsernameFromToken(token); // 파싱부분
        Member member = memberService.memberSelectById(memberIdx);
        board.setMember(member);
        Board result = boardService.save(board);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/api/board/{boardIdx}")
    public ResponseEntity<Board> getBoard(@PathVariable int boardIdx) {
        System.out.println(boardIdx);
        Board board = boardService.getBoardIdx(boardIdx);
        return ResponseEntity.ok().body(board);
    }
}