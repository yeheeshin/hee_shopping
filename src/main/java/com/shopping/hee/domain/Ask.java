package com.shopping.hee.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class Ask {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aseq;

    private String title;
    private String content;
    private LocalDate ADate;

    @ManyToOne
    @JoinColumn(name = "mseq")
    private Member member;
}
