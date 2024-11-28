package com.king.projectbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "bookmark")
@Getter
public class BookMark {
    @Id
    @GeneratedValue
    @Column(name = "bookMark_idx")
    private Long bookMarkIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberIdx")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diaryIdx")
    private Comment comment;


}
