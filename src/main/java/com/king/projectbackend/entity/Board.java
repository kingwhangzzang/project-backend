package com.king.projectbackend.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "board")
@Data
@ToString(exclude = "board")

public class Board {

    @Id
    @GeneratedValue
    @Column(name = "boardIdx")
    private Long boardIdx;

    @Column(name = "boardTitle")
    private String boardTitle;

    @Column(name = "boardContent")
    private String boardContent;

    @Column(name = "boardEnrollerDate")
    private LocalDateTime boardEnrollerDate;

    @Column(name = "disclosure")
    private String disclosure; //공개여부

    @Column(name = "boardLike")
    private Integer boardLike;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberIdx")
    @JsonIgnoreProperties({"boards", "followers", "followees", "blacklists", "bookmarks"})
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TagMapping> tagMappings;
}
