package com.shopping.hee.controller;

import com.shopping.hee.domain.Cart;
import com.shopping.hee.domain.Member;
import com.shopping.hee.service.CartService;
import com.shopping.hee.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shopping")
public class CartController {
    private final CartService cartService;
    private final MemberService memberService;

    // 장바구니 페이지 이동
    @GetMapping("/cart")
    public String cart(Model model) {
        Member member = memberService.nowMember();
        List<Cart> cartByMember = cartService.findCartByMember(member);

        int totalPrice = cartService.calculateTotal(cartByMember);

        model.addAttribute("cartByMember", cartByMember);
        model.addAttribute("totalPrice", totalPrice);

        return "my/cart";
    }

    // 장바구니 추가
    @PostMapping("/cartAdd")
    public ResponseEntity<String> cartAdd(@RequestParam("itemId") Long itemId, @RequestParam("quantity") int count) {
        try {
            cartService.addCart(itemId, count);
            return ResponseEntity.ok("장바구니에 상품이 추가되었습니다.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
