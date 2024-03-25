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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oseq")
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iseq")
    private Item item;
}
