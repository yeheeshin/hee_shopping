package com.shopping.hee.service;

import com.shopping.hee.domain.OrderDetail;
import com.shopping.hee.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetail saveOrderItem(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }
}
