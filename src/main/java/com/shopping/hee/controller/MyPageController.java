package com.shopping.hee.controller;

import com.shopping.hee.domain.Member;
import com.shopping.hee.domain.Orders;
import com.shopping.hee.service.MemberService;
import com.shopping.hee.service.OrderDetailService;
import com.shopping.hee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MyPageController {
    private final MemberService memberService;
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;
    private final PasswordEncoder passwordEncoder;

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
        orderService.updateOrder();
        List<Orders> orders = orderService.orderListByMember();
        List<Integer> num = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
           num.add(orderDetailService.countOrderItem(orders.get(i)));
        }

        model.addAttribute("num", num);
        model.addAttribute("orders", orders);

        return "my/orderList";
    }

    // 내 정보 수정
    @GetMapping("/memEdit")
    public String memberEditPage(Model model) {
        Member member = memberService.nowMember();
        model.addAttribute("member", member);

        return "my/memberEdit";
    }

    // 내 정보 수정
    @PostMapping("/memEdit")
    public String memberEdit(Model model, @ModelAttribute Member member, RedirectAttributes redirectAttributes) {
        memberService.memEdit(member.getMseq(),member);
        redirectAttributes.addFlashAttribute("errorMessage", "수정이 완료되었습니다.");
        return "redirect:/member/memEdit";
    }

}
