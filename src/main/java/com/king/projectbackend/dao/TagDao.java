package com.king.projectbackend.dao;

import com.king.projectbackend.entity.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TagDao {
    private final EntityManager em;

    public Tag findByTagName(String tagName) {
        try {
            return em.createQuery("SELECT t FROM Tag t WHERE t.tagName = :tagName", Tag.class)
                    .setParameter("tagName", tagName)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // 태그가 없으면 null 반환
        }
    }

    public Tag save(Tag tag) {
        em.persist(tag);
        return tag;
    }
}
