package com.shopping.hee.repository;

import com.shopping.hee.domain.Cart;
import com.shopping.hee.domain.Item;
import com.shopping.hee.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    // 해당 사용자의 장바구니에 동일한 상품이 있는지 확인
    boolean existsByMemberAndItem(Member member, Item item);
}
