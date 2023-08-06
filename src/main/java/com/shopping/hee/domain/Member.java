package com.shopping.hee.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long m_id;

    private String id;
    private String pwd;

    @Column(name = "m_name")
    private String name;

    private String phone;

    @Embedded
    private Address address;
}