package com.king.projectbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "blackList")
@Getter
public class BlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 수정
    @Column(name = "blackIdx")
    private Long blackIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberIdx")
    private Member member;
}