package com.shopping.hee.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iSeq;

    private String name;
    private int price;
    private String detail;
    private int count;
    private String img;
    private int size;

    @Enumerated(EnumType.STRING)
    private YesNo deleteStatus;

    @Enumerated(EnumType.STRING)
    private ItemColor color;

    @Enumerated(EnumType.STRING)
    private Category category;
}
