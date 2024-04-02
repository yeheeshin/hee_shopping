package com.shopping.hee.controller;

import com.shopping.hee.domain.Member;
import com.shopping.hee.domain.Orders;
import com.shopping.hee.service.MemberService;
import com.shopping.hee.service.OrderDetailService;
import com.shopping.hee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MyPageController {
    private final MemberService memberService;
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    // 마이페이지 이동
    @GetMapping("/my")
    public String my(Model model) {
        Member nowMember = memberService.nowMember();
        model.addAttribute("nowMember", nowMember);

        return "my/mypage";
    }

    // 주문 목록 페이지 이동
    @GetMapping("/o_l")
    public String orderList(Model model) {
        List<Orders> orders = orderService.orderListByMember();
        List<Integer> num = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
           num.add(orderDetailService.countOrderItem(orders.get(i)));
        }

        model.addAttribute("num", num);
        model.addAttribute("orders", orders);

        return "my/orderList";
    }

    @GetMapping("/memEdit")
    public String memberEdit(Model model) {
        return "my/memberEdit";
    }

}
