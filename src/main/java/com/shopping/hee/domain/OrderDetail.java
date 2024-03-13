package com.shopping.hee.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long odseq;

    // 상품 구매 시의 가격
    private int price;
    private int count;

    @ManyToOne
    @JoinColumn(name = "oseq")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "iseq")
    private Item item;
}
