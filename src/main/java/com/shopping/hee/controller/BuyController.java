package com.shopping.hee.controller;

import com.shopping.hee.domain.Cart;
import com.shopping.hee.domain.Item;
import com.shopping.hee.domain.Member;
import com.shopping.hee.service.CartService;
import com.shopping.hee.service.ItemService;
import com.shopping.hee.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shopping")
public class BuyController {
    private final ItemService itemService;
    private final MemberService memberService;
    private final CartService cartService;

    // 구매하기 페이지로 이동(삭제예정)
    @GetMapping("/o_d")
    public String orderDetail(Model model) {
        return "itemBuy";
    }

    // 상품 페이지에서 구매하기 버튼을 눌렀을 때
    @PostMapping("/buy")
    public String itemBuy(@RequestParam("itemId") Long seq, @RequestParam("quantity") int count, Model model) {
        List<Item> items = itemService.getOneItem(seq);

        Member member = memberService.nowMember();

        model.addAttribute("items", items);
        model.addAttribute("count", count);
        model.addAttribute("member", member);

        return "itemBuy";
    }
}
