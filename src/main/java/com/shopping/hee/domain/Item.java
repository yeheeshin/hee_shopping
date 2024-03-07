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
    private String color;
    private int size;
    // 팔찌, 반지 등 구별 코드
    private int code;

    @Enumerated(EnumType.STRING)
    private YesNo deleteStatus;

    @ManyToOne
    @JoinColumn(name = "cSeq")
    private Category category;
}
