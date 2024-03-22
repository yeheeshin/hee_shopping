package com.shopping.hee.controller;

import com.shopping.hee.domain.Item;
import com.shopping.hee.domain.Member;
import com.shopping.hee.service.ItemService;
import com.shopping.hee.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shopping") // 추후에 order 로 바꾸기
public class OrderController {

    private final ItemService itemService;
    private final MemberService memberService;

    // 상품 페이지에서 구매하기 버튼을 눌렀을 때(아이템 1개)
    @PostMapping("/buy")
    public String itemBuy(@RequestParam("itemId") Long seq, @RequestParam("quantity") int count, Model model) {
        List<Item> items = itemService.getOneItem(seq);

        Member member = memberService.nowMember();

        model.addAttribute("items", items);
        model.addAttribute("count", count);
        model.addAttribute("member", member);

        return "itemBuy";
    }

    @PostMapping("itemsBuy")
    public String itemsBuy(Model model) {


        return "itemBuy";
    }
}
