package com.shopping.hee.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Wish {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wSeq;

    @ManyToOne
    @JoinColumn(name = "mSeq")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "iSeq")
    private Item item;
}
