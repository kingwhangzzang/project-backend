package com.king.projectbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@Data
public class Member implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "memberIdx")
    private Long memberIdx;

    @Column(name = "memberLoginId", unique = true)
    private String memberLoginId;

    @Column(name = "memberPassword")
    private String memberPassword;

    @Column(name = "memberNickname", unique = true)
    private String memberNickname;

    @Column(name = "memberName")
    private String memberName;

    @Column(name = "memberTel")
    private String memberTel;

    @Column(name = "memberBirth")
    private LocalDate memberBirth;

    @Column(name = "memberIsDeleted")
    private String memberIsDeleted;

    @Column(name = "memberEnrollDate")
    private LocalDate memberEnrollDate;

    @PrePersist
    public void prePersist(){
        if(memberEnrollDate == null){
            memberEnrollDate = LocalDate.now();
        }
    }
    @Column(name = "memberProfile")
    private String memberProfile; // 수정

    @Column(name = "memberRole")
    private String memberRole; // 수정

    @OneToMany(mappedBy = "followee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Follow> followers = new ArrayList<>();

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Follow> followees = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BlackList> blacklists = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookMark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(()->"ROLE_USER");
    }

    @Override
    public String getPassword() {
        return memberPassword;
    }

    @Override
    public String getUsername() {
        return memberLoginId;
    }
}