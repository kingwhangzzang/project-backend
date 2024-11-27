package com.king.projectbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Table(name = "follow")
@Entity
@Getter
public class FollowEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "followIdx")
    private Long followIdx;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "followerId")
    private MemberEntity follower;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "followeeId")
    private MemberEntity followee;




}
