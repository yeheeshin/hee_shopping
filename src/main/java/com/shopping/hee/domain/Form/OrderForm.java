package com.shopping.hee.domain.Form;

import com.shopping.hee.domain.Item;
import com.shopping.hee.domain.OrderDetail;
import com.shopping.hee.domain.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
public class OrderForm {
    private Long iseq;
    private int count;

    public OrderForm(Long iseq, int count) {
        this.iseq = iseq;
        this.count = count;
    }

    public static OrderDetail createOrderDetail(Item item, int count, Orders orders) throws IOException {
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setPrice(item.getPrice());
        orderDetail.setCount(count);
        orderDetail.setOrder(orders);
        orderDetail.setItem(item);

        return orderDetail;
    }

}
