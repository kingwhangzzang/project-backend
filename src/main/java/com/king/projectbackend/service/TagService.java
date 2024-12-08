package com.king.projectbackend.service;

import com.king.projectbackend.dao.TagDao;
import com.king.projectbackend.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagDao tagDao;
    @Transactional
    public List<Tag> getTags(){
        List<Tag> tags = tagDao.getTags();
        return tags;
    }
}
