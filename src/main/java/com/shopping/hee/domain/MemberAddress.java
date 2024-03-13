package com.shopping.hee.domain;

import com.shopping.hee.domain.Enum.YesNo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class MemberAddress {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maseq;

    @Enumerated(EnumType.STRING)
    private YesNo basic;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "mseq")
    private Member member;
}
