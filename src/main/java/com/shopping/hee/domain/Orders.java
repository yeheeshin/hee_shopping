package com.shopping.hee.domain;

import com.shopping.hee.domain.Enum.HowPay;
import com.shopping.hee.domain.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class Orders {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oseq;

    private LocalDate oDate;
    private int total;
    private String pName;
    private String pImg;

    @Enumerated(EnumType.STRING)
    private HowPay howPay;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "mseq")
    private Member member;
}
