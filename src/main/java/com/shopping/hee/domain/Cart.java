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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mseq")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iseq")
    private Item item;

}
