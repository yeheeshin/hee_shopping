package com.shopping.hee.repository;

import com.shopping.hee.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // 중복 상품명을 위한 상품명 검색
    Item findByName(String name);
}
