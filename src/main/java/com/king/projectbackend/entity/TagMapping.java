package com.king.projectbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "tagIdx")
    @JsonIgnoreProperties("tagMapping")
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardIdx")
    @JsonBackReference
    private Board board;
}
