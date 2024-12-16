package com.king.projectbackend.service;

import com.king.projectbackend.dao.BoardDao;
import com.king.projectbackend.dao.TagDao;
import com.king.projectbackend.dao.TagMappingDao;
import com.king.projectbackend.entity.Board;
import com.king.projectbackend.entity.Member;
import com.king.projectbackend.entity.Tag;
import com.king.projectbackend.entity.TagMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardDao boardDao;
    private final TagDao tagDao;
    private final TagMappingDao tagMappingDao;
    private final MemberService memberService;
    public Board save(Board board) {
        Board result = boardDao.save(board);
        return result;
    }

    public List<Board> boardFindByDisClosure(String disclosure) {
        List<Board> boards = boardDao.boardFindByDisClosure(disclosure);
        for (Board board : boards) {
            String endcode = board.getMember().getMemberNickname();
            String originName = memberService.decodeGetOne(endcode);
            Member member = new Member();
            member.setMemberNickname(originName);
            board.setMember(member);
        }
        return boards;
    }

    public Board getBoardIdx(int boardIdx) {
        return boardDao.boardFindByIdx(boardIdx);
    }
}
