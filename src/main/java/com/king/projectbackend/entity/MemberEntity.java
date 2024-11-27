package com.king.projectbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table
@Entity
@Getter
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "memberIdx")
    private Long memberIdx;

    @Column(name = "memberLoginId",unique = true)
    private String memberLoginId;

    @Column(name = "memberPassword")
    private String memberPassword;

    @Column(name = "memberNickname",unique = true)
    private String memberNickname;

    @Column(name = "memberEmail")
    private String memberEmail;

    @Column(name = "memberName")
    private String memberName;

    @Column(name = "memberTel")
    private String memberTel;

    @Column(name ="memberBirth")
    private String memberBirth;

    @Column(name = "memberIsDeleted")
    private String memberIsDeleted;

    @Column(name = "memberEnrollDate")
    private Date memberEnrollDate;

    @Column(name = "memberProfile")
    public String memberProfile; //수정 필요

    @Column(name = "memberRole")
    public String memberRole;

    @OneToMany(mappedBy = "followee")
    private Set<FollowEntity> followers = new HashSet<>();

    @OneToMany(mappedBy = "follower")
    private Set<FollowEntity> followees = new HashSet<>();

    @OneToMany(mappedBy = "memberEntity")
    private Set<BlackList> blacklists = new HashSet<>();

}
