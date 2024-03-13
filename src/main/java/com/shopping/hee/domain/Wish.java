package com.shopping.hee.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Wish {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wseq;

    @ManyToOne
    @JoinColumn(name = "mseq")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "iseq")
    private Item item;
}
