package com.shopping.hee.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cSeq;

    private String name;

}
