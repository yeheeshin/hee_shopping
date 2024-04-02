package com.shopping.hee.repository;

import com.shopping.hee.domain.OrderDetail;
import com.shopping.hee.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    // 주문 별 상품의 갯수
    int countByOrder(Orders orders);

    // 주문으로 주문 디테일 찾기
    List<OrderDetail> findByOrder(Orders orders);
}
