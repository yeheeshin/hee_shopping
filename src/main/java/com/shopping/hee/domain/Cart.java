package com.shopping.hee.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartSeq;

    private int count;
    private LocalDate cartDate;

    @ManyToOne
    @JoinColumn(name = "mSeq")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "iSeq")
    private Item item;

}
