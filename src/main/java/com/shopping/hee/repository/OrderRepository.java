package com.shopping.hee.repository;

import com.shopping.hee.domain.Enum.OrderStatus;
import com.shopping.hee.domain.Member;
import com.shopping.hee.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByMember(Member member);

    Orders findByOseq(Long seq);

    // 주문 상태에 따른 주문의 갯수
    int countByMemberAndStatus(Member member, OrderStatus orderStatus);
}
