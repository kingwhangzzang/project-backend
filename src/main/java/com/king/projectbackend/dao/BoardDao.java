package com.king.projectbackend.dao;

import com.king.projectbackend.entity.Board;
import com.king.projectbackend.entity.Tag;
import com.king.projectbackend.entity.TagMapping;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDao {

    private final EntityManager em;
    private final TagDao tagDao;  // TagDao 주입

    @Transactional
    public Board save(Board board) {
        // 1. Board 저장
        em.persist(board);

        // 2. TagMapping 처리
        if (board.getTagMappings() != null) {
            for (TagMapping tagMapping : board.getTagMappings()) {
                Tag tag = tagMapping.getTag();
                if (tag == null) {
                    throw new IllegalArgumentException("Tag cannot be null in TagMapping");
                }

                // 기존 태그가 있으면 재사용, 없으면 새로 저장
                Tag existingTag = tagDao.findByTagName(tag.getTagName());
                if (existingTag != null) {
                    tag = existingTag;  // 기존 태그 사용
                } else {
                    if (tag.getTagIdx() == null) {
                        em.persist(tag); // 새로 생성된 태그 저장
                    }
                }

                // TagMapping에 Board와 Tag 설정
                tagMapping.setBoard(board);
                tagMapping.setTag(tag);

                em.persist(tagMapping); // TagMapping 저장
            }
        }

        // 3. flush 호출
        em.flush();
        return board;
    }

    public List<Board> boardFindByDisClosure(String disclosure) {
    return em.createQuery("SELECT b FROM Board b join fetch b.member where b.disclosure = :disclosure",Board.class)
            .setParameter("disclosure",disclosure)
            .getResultList();
    }

    public Board boardFindByIdx(int boardIdx) {
        return em.find(Board.class, boardIdx);
    }
}