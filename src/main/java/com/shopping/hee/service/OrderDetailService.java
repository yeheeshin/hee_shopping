package com.shopping.hee.service;

import com.shopping.hee.domain.OrderDetail;
import com.shopping.hee.domain.Orders;
import com.shopping.hee.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetail saveOrderItem(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    // 주문 별 주문한 상품의 갯수
    public int countOrderItem(Orders orders) {
        int countByOrder = orderDetailRepository.countByOrder(orders);

        return countByOrder-1;
    }

    // 주문으로 주문디테일 리스트 찾기
    public List<OrderDetail> findOrderDetail(Orders orders) {
        return orderDetailRepository.findByOrder(orders);
    }
}
