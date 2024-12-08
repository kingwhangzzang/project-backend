package com.king.projectbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "commenttag")
@Getter
@Setter
@Data
public class CommentTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentTagIdx")
    private Long commentTagIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentIdx", nullable = false)
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tagIdx", nullable = false)
    private Tag tag;
}