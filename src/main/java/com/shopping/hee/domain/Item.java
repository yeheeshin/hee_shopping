package com.shopping.hee.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long i_id;

    private String name;
    private int price;
    private String detail;
    private String i_code;
    private int count;
    private String img;

    @Enumerated(EnumType.STRING)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
