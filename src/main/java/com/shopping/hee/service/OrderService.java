package com.shopping.hee.service;

import com.shopping.hee.domain.Orders;
import com.shopping.hee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    // 주문
    public Orders saveOrder(Orders orders) {
        return orderRepository.save(orders);
    }
}
