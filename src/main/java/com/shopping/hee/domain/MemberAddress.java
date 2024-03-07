package com.shopping.hee.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class MemberAddress {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maSeq;

    @Enumerated(EnumType.STRING)
    private YesNo basic;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "mSeq")
    private Member member;
}
