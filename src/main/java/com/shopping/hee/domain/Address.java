package com.shopping.hee.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Address {

    private String name;
    private String phone;
    private String street;
    private String city;
    private String zipCode;

    public Address() {
        // 기본 생성자
    }

    public Address(String name, String phone, String street, String city, String zipCode) {
        this.name = name;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }
}

