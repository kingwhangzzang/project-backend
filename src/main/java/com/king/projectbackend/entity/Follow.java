package com.king.projectbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "follow")
@Getter
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "followIdx")
    private Long followIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followerIdx") // 수정
    private Member follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followeeIdx") // 수정
    private Member followee;
}