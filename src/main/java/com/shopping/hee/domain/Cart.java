package com.shopping.hee.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartseq;

    private int count;

    @ManyToOne
    @JoinColumn(name = "mseq")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "iseq")
    private Item item;

}
