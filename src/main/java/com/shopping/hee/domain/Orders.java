package com.shopping.hee.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class Orders {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long o_id;

    private LocalDate o_date;
    private int count;

    @Column(name = "m_name")
    private String name;
    private int price;

    @Embedded
    private Address address;

    @Embedded
    private ItemOption option;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "m_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "i_id")
    private Item item;

}
