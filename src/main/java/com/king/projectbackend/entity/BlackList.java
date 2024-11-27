package com.king.projectbackend.entity;

import jakarta.persistence.*;

@Table(name = "blackList")
@Entity
public class BlackList {

    @Id
    @Column(name = "blackIdx")
    private Long blackIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberIdx")
    private MemberEntity memberEntity;


}
