package com.shopping.hee.service;

import com.shopping.hee.domain.Item;
import com.shopping.hee.domain.Member;
import com.shopping.hee.domain.Orders;
import com.shopping.hee.repository.CartRepository;
import com.shopping.hee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    // 주문
    public Orders saveOrder(Orders orders) {
        return orderRepository.save(orders);
    }

    // 주문 후, 주문한 아이템 삭제
    public void deleteCartItem(Member member, Item item) {
        cartRepository.deleteByMemberAndItem(member, item);
    }

}
