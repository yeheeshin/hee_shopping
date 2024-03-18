package com.shopping.hee.controller;

import com.shopping.hee.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    // 장바구니 페이지 이동
    @GetMapping("/cart")
    public String cart(Model model) {
        return "my/cart";
    }

    // 장바구니 추가
    @PostMapping("/cartAdd")
    public String cartAdd(@RequestParam("itemId") Long itemId, @RequestParam("quantity") int count, RedirectAttributes redirectAttributes) {
        try {
            cartService.addCart(itemId, count);
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/i_d?id=" + itemId;
        }

        return "my/cart";
    }


}
