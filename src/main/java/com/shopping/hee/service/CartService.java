package com.shopping.hee.service;

import com.shopping.hee.domain.Cart;
import com.shopping.hee.domain.Item;
import com.shopping.hee.domain.Member;
import com.shopping.hee.repository.CartRepository;
import com.shopping.hee.repository.ItemRepository;
import com.shopping.hee.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ItemService itemService;

    public Cart addCart(Long itemId, int count) {
        // 현재 사용자의 정보
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 사용자 아이디를 가져옴
        String userId = authentication.getName();

        Member member = memberRepository.findByEmail(userId);
        Item item = itemService.findById(itemId);

        validateDuplicateCart(member, item);

        Cart cart = new Cart();
        cart.setItem(item);
        cart.setCount(count);
        cart.setMember(member);

        return cartRepository.save(cart);
    }

    // 해당 사용자의 장바구니에 동일한 상품이 존재하는 지 확인
    private void validateDuplicateCart(Member member, Item item) {
        boolean findCart = cartRepository.existsByMemberAndItem(member, item);

        if (findCart) {
            throw new IllegalStateException("이미 동일한 상품이 장바구니에 있습니다");
        }
    }
}
