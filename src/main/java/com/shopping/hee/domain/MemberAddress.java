package com.shopping.hee.domain;

import com.shopping.hee.domain.Enum.YesNo;
import com.shopping.hee.domain.Form.ItemForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Entity
@Getter @Setter
public class MemberAddress {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maseq;
    private String title;

    @Enumerated(EnumType.STRING)
    private YesNo basic;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "mseq")
    private Member member;
}
