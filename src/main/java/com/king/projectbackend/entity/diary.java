package com.king.projectbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "diary")
@Getter
public class diary {
    @Id
    @Column(name = "dirayIdx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dirayIdx;

    @Column(name = "diaryTitile")
    private String diaryTitle;

    @Column(name = "diaryComment")
    private String diaryComment; //string 말고 xml형식으로 삽입

}
