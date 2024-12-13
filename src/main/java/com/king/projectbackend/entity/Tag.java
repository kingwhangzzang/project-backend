package com.king.projectbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tag")
@Data
public class Tag {

    @Id
    @GeneratedValue
    @Column(name = "tagIdx")
    private Long tagIdx;

    @Column(name = "tagName", nullable = false)
    private String tagName;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<TagMapping> tagMapping;
}
