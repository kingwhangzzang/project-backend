package com.king.projectbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tag")
@Getter
@Setter
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tagIdx")
    private Long tagIdx;

    @Column(name = "tagName", unique = true, nullable = false)
    private String tagName;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentTag> commentTags = new ArrayList<>();

    public Tag() {}

    public Tag(String tagName) {
        this.tagName = tagName;
    }
}