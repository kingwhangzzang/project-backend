package com.king.projectbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "board")
@Data
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "boardIdx")
    private Long boardIdx;

    @Column(name = "boardTitle")
    private String boardTitle;

    @Column(name = "boardContent")
    private String boardContent;

    @Column(name = "boardEnrollerDate")
    private LocalDateTime boardEnrollerDate;

    @Column(name = "disclosure")
    private String disclosure; //공개여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberIdx")  // 게시글을 작성한 회원을 참조
    private Member member;  // 하나의 게시글은 하나의 회원에 속함

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TagMapping> tagMappings;
}
