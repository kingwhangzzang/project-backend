package com.king.projectbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comment")
@Getter
@Setter
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentIdx")
    private Long commentIdx;

    @Column(name = "commentTitle")
    private String commentTitle;

    @Column(name = "disclosure")
    private String disclosure;

    @Column(name = "commentContent")
    private String commentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentCommentIdx")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> childComments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberIdx")
    private Member member;

    @Column(name = "commentIsDeleted")
    private Boolean commentIsDeleted = false;

    @Column(name = "commentLike")
    private Integer commentLike = 0;

    @Column(name = "commentDepth")
    private Integer commentDepth;

    @Column(name = "commentOrderNumber")
    private Integer commentOrderNumber;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentTag> commentTags = new ArrayList<>();
}