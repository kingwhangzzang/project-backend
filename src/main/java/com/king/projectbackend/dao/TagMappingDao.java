package com.king.projectbackend.dao;

import com.king.projectbackend.entity.TagMapping;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TagMappingDao {
    private final EntityManager em;

    public TagMapping save(TagMapping tagMapping) {
        if (tagMapping.getTagMappingIdx() == null) {
            em.persist(tagMapping); // 새 매핑 저장
            return tagMapping;
        } else {
            return em.merge(tagMapping); // 기존 매핑 업데이트
        }
    }
}
