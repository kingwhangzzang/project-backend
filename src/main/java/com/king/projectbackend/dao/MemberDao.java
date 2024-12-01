package com.king.projectbackend.dao;

import com.king.projectbackend.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDao {
    private final EntityManager em;
    public boolean insertMember(Member member){
        try {
            em.persist(member);
            return true;
        }catch (PersistenceException e){
            e.printStackTrace();
            return false;
        }

    }
}
