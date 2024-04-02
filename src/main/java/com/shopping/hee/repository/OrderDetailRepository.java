package com.shopping.hee.repository;

import com.shopping.hee.domain.OrderDetail;
import com.shopping.hee.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    // 주문 별 상품의 갯수
    int countByOrder(Orders orders);
}
