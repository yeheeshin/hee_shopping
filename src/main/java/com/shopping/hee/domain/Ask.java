package com.shopping.hee.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class Ask {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aseq;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    @LastModifiedDate
    private LocalDate adate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mseq")
    private Member member;
}
