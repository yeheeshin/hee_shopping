package com.shopping.hee.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mSeq;

    private String email;
    private String pwd;
    private String name;
    private String phone;

    @Enumerated(EnumType.STRING)
    private YesNo deleteStatus;
}