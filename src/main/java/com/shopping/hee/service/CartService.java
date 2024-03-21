package com.shopping.hee.service;

import com.shopping.hee.domain.Cart;
import com.shopping.hee.domain.Item;
import com.shopping.hee.domain.Member;
import com.shopping.hee.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final MemberService memberService;
    private final ItemService itemService;

    public Cart addCart(Long itemId, int count) {

        Member member = memberService.nowMember();
        Item item = itemService.findById(itemId);

        validateDuplicateCart(member, item);

        Cart cart = new Cart();
        cart.setItem(item);
        cart.setCount(count);
        cart.setMember(member);

        return cartRepository.save(cart);
    }

    public List<Cart> findCartByMember(Member member) {
        return cartRepository.findByMember(member);
    }

    // 해당 사용자의 장바구니에 동일한 상품이 존재하는 지 확인
    private void validateDuplicateCart(Member member, Item item) {
        boolean findCart = cartRepository.existsByMemberAndItem(member, item);

        if (findCart) {
            throw new IllegalStateException("이미 동일한 상품이 장바구니에 있습니다");
        }
    }
}
