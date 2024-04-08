package com.shopping.hee.service;

import com.shopping.hee.domain.Enum.OrderStatus;
import com.shopping.hee.domain.Item;
import com.shopping.hee.domain.Member;
import com.shopping.hee.domain.Orders;
import com.shopping.hee.repository.CartRepository;
import com.shopping.hee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final MemberService memberService;

    // 주문
    public Orders saveOrder(Orders orders) {
        return orderRepository.save(orders);
    }

    // 주문 후, 주문한 아이템 삭제
    public void deleteCartItem(Member member, Item item) {
        cartRepository.deleteByMemberAndItem(member, item);
    }

    // 회원 별 주문 리스트
    public List<Orders> orderListByMember() {
        return orderRepository.findByMember(nowMember());
    }

    // 주문 seq 로 주문 검색
    public Orders findOrderDetail(Long seq) {
        return orderRepository.findByOseq(seq);
    }

    // 배송 상태 업데이트
    public void updateOrder() {
        LocalDate currentDate = LocalDate.now();
        List<Orders> allOrders = orderRepository.findAll();

        for (Orders order : allOrders) {
            updateOrderStatus(order, currentDate);
        }
    }

    // 상품의 재고보다 더 많이 구해하는가?
    public void overItem(Item item, int count) {
        if (item.getCount() < count) {
            throw new IllegalStateException("재고보다 더 많은 수량을 선택하셨습니다. 남은 재고 : " + item.getCount());
        }
    }

    /**
     * 주문 상태에 따른 주문의 갯수
     */
    // 결제 완료
    public int countPay() {
        OrderStatus orderStatus = OrderStatus.PAYMENT_COMPLETED;

        return orderRepository.countByMemberAndStatus(nowMember(), orderStatus);
    }

    // 배송중
    public int countDelivery() {
        OrderStatus delivery = OrderStatus.DELIVERY;

        return orderRepository.countByMemberAndStatus(nowMember(), delivery);
    }

    // 배송 완료
    public int countDeliveryCompleted() {
        OrderStatus deliveryCompleted = OrderStatus.DELIVERY_COMPLETED;

        return orderRepository.countByMemberAndStatus(nowMember(), deliveryCompleted);
    }



    private void updateOrderStatus(Orders order, LocalDate currentDate) {
        LocalDate oneDayAfterOrderDate = order.getOdate().plusDays(1);
        LocalDate twoDaysAfterOrderDate = order.getOdate().plusDays(2);

        // 이미 배송 완료인 경우 추가 업데이트를 방지하기 위해 추가 조건 검사
        if (order.getStatus() == OrderStatus.DELIVERY_COMPLETED) {
            return; // 이미 배송 완료이므로 추가 업데이트를 하지 않음
        }

        if (currentDate.isEqual(oneDayAfterOrderDate) || currentDate.isAfter(oneDayAfterOrderDate)) {
            order.setStatus(OrderStatus.DELIVERY); // 배송중으로 업데이트
        }

        if (currentDate.isEqual(twoDaysAfterOrderDate) || currentDate.isAfter(twoDaysAfterOrderDate)) {
            order.setStatus(OrderStatus.DELIVERY_COMPLETED); // 배송 완료로 업데이트
        }

        orderRepository.save(order);
    }

    private Member nowMember() {
        return memberService.nowMember();
    }

}
