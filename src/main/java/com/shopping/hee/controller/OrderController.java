package com.shopping.hee.controller;

import com.shopping.hee.domain.Form.OrderForm;
import com.shopping.hee.domain.Form.OrderItemsForm;
import com.shopping.hee.domain.Item;
import com.shopping.hee.domain.Member;
import com.shopping.hee.service.ItemService;
import com.shopping.hee.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shopping") // 추후에 order 로 바꾸기
public class OrderController {

    private final ItemService itemService;
    private final MemberService memberService;

//    // 상품 페이지에서 구매하기 버튼을 눌렀을 때(아이템 1개)
//    @PostMapping("/buy")
//    public String itemBuy(@RequestParam("itemId") Long seq, @RequestParam("quantity") int count, Model model) {
//        List<Item> items = itemService.getOneItem(seq);
//
//        Member member = memberService.nowMember();
//
//        model.addAttribute("items", items);
//        model.addAttribute("count", count);
//        model.addAttribute("member", member);
//
//        return "itemBuy";
//    }

//    // 장바구니에서 선택된 아이템 정보들 --> 주문하기 페이지로 이동
//    @PostMapping("/itemsBuy")
//    public String itemsBuy(@RequestParam(value = "iseq", required = false) List<String> iseqList,
//                                  @RequestParam(value = "quantity", required = false) List<String> quantityList, Model model) {
//        List<OrderForm> orderItems = new ArrayList<>();
//        int iseqSize = iseqList.size();
//        System.out.println("iseqSize = " + iseqSize);
//
//
//        List<Long> iseqLongs = iseqList.stream()
//                .map(Long::parseLong)
//                .collect(Collectors.toList());
//
//        List<Integer> quantities = quantityList.stream()
//                .map(Integer::parseInt)
//                .collect(Collectors.toList());
//
//        for (int i = 0; i < iseqSize; i++) {
//            Long seq = iseqLongs.get(i);
//            Item items = itemService.findById(seq);
//            int count = quantities.get(i);
//
//            if (items != null) {
//                orderItems.add(new OrderForm(items, count));
//            }
//        }
//
//        Member member = memberService.nowMember();
//
//        model.addAttribute("orderItems", orderItems);
//        model.addAttribute("member", member);
//
//        return "itemBuy";
//    }

    //
    @PostMapping("/itemsBuy1")
    @ResponseBody
    public void itemsBuy(@RequestParam("iseq") List<Long> iseqList, @RequestParam("quantity") List<Integer> quantityList, HttpSession session) {
        System.out.println("지났다");

        session.setAttribute("iseqList", iseqList);
        session.setAttribute("quantityList", quantityList);
    }

    @GetMapping("/itemsBuy2")
    public String buyPage(Model model, HttpSession session) {
        List<OrderItemsForm> orderItemsForms = new ArrayList<>();

        List<Long> iseqList = (List<Long>) session.getAttribute("iseqList");
        List<Integer> quantityList = (List<Integer>) session.getAttribute("quantityList");

        for (int i = 0; i < iseqList.size(); i++) {
            Long iseq = iseqList.get(i);
            int count = quantityList.get(i);

            Item item = itemService.findById(iseq);

            orderItemsForms.add(new OrderItemsForm(item, count));
        }

        Member member = memberService.nowMember();

        model.addAttribute("member", member);
        model.addAttribute("orderItemsForms", orderItemsForms);

        return "Item/itemBuy";
    }
}
