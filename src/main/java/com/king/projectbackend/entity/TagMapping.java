package com.king.projectbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tagMapping")
@Data
public class TagMapping {
    @Id
    @GeneratedValue
    @Column(name = "tagMappingIdx")
    private Long TagMappingIdx;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "tagIdx")
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardIdx")
    private Board board;
}
