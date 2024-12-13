package com.king.projectbackend.dao;

import com.king.projectbackend.entity.Board;
import com.king.projectbackend.entity.Tag;
import com.king.projectbackend.entity.TagMapping;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class BoardDao {

    private final EntityManager em;

    @Transactional
    public Board save(Board board) {
        // 1. Board 저장
        em.persist(board);

        // 2. TagMapping 처리
        if (board.getTagMappings() != null) {
            for (TagMapping tagMapping : board.getTagMappings()) {
                // Tag 검증 및 저장
                Tag tag = tagMapping.getTag();
                if (tag == null) {
                    throw new IllegalArgumentException("Tag cannot be null in TagMapping");
                }

                if (tag.getTagIdx() == null) {
                    em.persist(tag); // 새로 생성된 태그만 저장
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
}