package com.shopping.hee.domain.Form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderForm {
    private String iseq;
    private String count;

    public OrderForm(String iseq, String count) {
        this.iseq = iseq;
        this.count = count;
    }
}
