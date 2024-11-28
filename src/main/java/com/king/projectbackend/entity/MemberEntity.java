package com.king.projectbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.*;

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
    private Date memberBirth;

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

    @OneToMany(mappedBy = "memberIdx")
    private Set<BlackList> blacklists = new HashSet<>();

    @OneToMany(mappedBy = "memberIdx")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "memberIdx")
    private List<BookMark> bookmarks = new ArrayList<>();
}
