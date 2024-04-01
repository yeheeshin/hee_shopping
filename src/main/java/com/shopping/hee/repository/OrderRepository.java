package com.shopping.hee.repository;

import com.shopping.hee.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
