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

    // 장바구니 추가
    public Cart addCart(Long itemId, int count) {

        Member member = memberService.nowMember();
        Item item = itemService.findById(itemId);

        validateDuplicateCart(member, item);
        overItem(item, count);

        Cart cart = new Cart();
        cart.setItem(item);
        cart.setCount(count);
        cart.setMember(member);

        return cartRepository.save(cart);
    }

    // 멤버 별 장바구니 조회
    public List<Cart> findCartByMember(Member member) {
        return cartRepository.findByMember(member);
    }

    // 총 가격 계산
    public int calculateTotal(List<Cart> carts) {
        int total = 0;
        for (Cart cart : carts) {
            total += cart.getItem().getPrice() * cart.getCount();
        }

        return total;
    }

    // 회원 별 장바구니 갯수 조회
    public int countMember(Member member) {
        return cartRepository.countByMember(member);
    }

    // 해당 사용자의 장바구니에 동일한 상품이 존재하는 지 확인
    private void validateDuplicateCart(Member member, Item item) {
        boolean findCart = cartRepository.existsByMemberAndItem(member, item);

        if (findCart) {
            throw new IllegalStateException("이미 동일한 상품이 장바구니에 있습니다");
        }
    }

    // 상품의 재고보다 더 많이 장바구니에 담는가?
    private void overItem(Item item, int count) {
        if (item.getCount() < count) {
            throw new IllegalStateException("재고보다 더 많은 수량을 선택하셨습니다. 남은 재고 : " + item.getCount());
        }
    }
}
