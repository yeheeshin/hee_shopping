package com.shopping.hee.domain.Form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderForm {
    private Long iseq;
    private int count;

    public OrderForm(Long iseq, int count) {
        this.iseq = iseq;
        this.count = count;
    }
}
