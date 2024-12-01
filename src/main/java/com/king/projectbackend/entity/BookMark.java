package com.king.projectbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "bookmark")
@Getter
public class BookMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookMarkIdx")
    private Long bookMarkIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberIdx")
    private Member member; // 수정

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentIdx")
    private Comment comment; // 수정
}