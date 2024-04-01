package com.shopping.hee.repository;

import com.shopping.hee.domain.Member;
import com.shopping.hee.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByMember(Member member);
}
