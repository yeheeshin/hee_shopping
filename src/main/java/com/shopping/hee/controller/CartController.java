package com.shopping.hee.controller;

import com.shopping.hee.domain.Item;
import com.shopping.hee.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    // 장바구니 추가
    @PostMapping("cartAdd")
    public String cartAdd(@RequestParam("itemName") String itemName, @RequestParam("quantity") int count, Model model) {
        cartService.addCart(itemName, count);

        return "my/cart";
    }
}
