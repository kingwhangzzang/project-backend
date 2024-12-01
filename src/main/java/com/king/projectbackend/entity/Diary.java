package com.king.projectbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "diary")
@Getter
public class Diary { // 클래스 이름 수정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diaryIdx") // 수정
    private Long diaryIdx;

    @Column(name = "diaryTitle")
    private String diaryTitle;

    @Lob
    @Column(name = "diaryComment")
    private String diaryComment; // XML 형식 저장
}