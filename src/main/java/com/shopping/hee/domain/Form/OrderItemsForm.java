package com.shopping.hee.domain.Form;

import com.shopping.hee.domain.Item;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemsForm {
    private Item item;
    private int count;

    public OrderItemsForm(Item item, int count) {
        this.item = item;
        this.count = count;
    }
}
