package com.king.projectbackend.dao;

import com.king.projectbackend.entity.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TagDao {
    private final EntityManager em;

    public List<Tag> getTags() {
        try {
           return em.createQuery("select t.tagName from Tag t", Tag.class).getResultList();
        }catch (PersistenceException e){
           e.printStackTrace();
            return null;
        }
    }
}
