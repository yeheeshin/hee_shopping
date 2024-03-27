package com.shopping.hee.controller;

import com.shopping.hee.domain.Form.OrderForm;
import com.shopping.hee.domain.Form.OrderItemsForm;
import com.shopping.hee.domain.Item;
import com.shopping.hee.domain.Member;
import com.shopping.hee.domain.MemberAddress;
import com.shopping.hee.service.ItemService;
import com.shopping.hee.service.MemberAddressService;
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
    private final MemberAddressService memberAddressService;

    // 상품 페이지에서 구매하기 버튼을 눌렀을 때(아이템 1개)
    @PostMapping("/buy")
    public String itemBuy(@RequestParam("iseq") Long seq, @RequestParam("quantity") int count, Model model) {
        Item item = itemService.findById(seq);
        List<OrderItemsForm> orderItemsForms = new ArrayList<>();
        orderItemsForms.add(new OrderItemsForm(item, count));

        Member member = memberService.nowMember();

        // 배송지 목록 불러오기
        List<MemberAddress> memberAddresses = memberAddressService.memberAddressList(member);

        model.addAttribute("member", member);
        model.addAttribute("orderItemsForms", orderItemsForms);
        model.addAttribute("mad", memberAddresses);

        return "Item/itemBuy";
    }

    // 장바구니에서 선택한 아이템 정보
    @PostMapping("/itemsBuy1")
    @ResponseBody
    public void itemsBuy(@RequestParam("iseq") List<Long> iseqList, @RequestParam("quantity") List<Integer> quantityList, HttpSession session) {
        System.out.println("지났다");

        session.setAttribute("iseqList", iseqList);
        session.setAttribute("quantityList", quantityList);
    }

    // 선택한 아이템들 정보 받아서 뷰에 뿌리기
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

        // 배송지 목록 불러오기
        List<MemberAddress> memberAddresses = memberAddressService.memberAddressList(member);

        model.addAttribute("member", member);
        model.addAttribute("orderItemsForms", orderItemsForms);
        model.addAttribute("mad", memberAddresses);

        session.removeAttribute("iseqList");
        session.removeAttribute("quantityList");

        return "Item/itemBuy";
    }

    // 선택한 배송지 가져오기
    @GetMapping("/selectAddress")
    @ResponseBody
    public MemberAddress getAddressDetail(@RequestParam Long seq) {
        MemberAddress addressBySeq = memberAddressService.getAddressBySeq(seq);

        return addressBySeq;
    }

    // 기본 배송지 가져오기
    @GetMapping("/getBasicAddress")
    @ResponseBody
    public MemberAddress getBasicAddress() {
        MemberAddress basicAddress = memberAddressService.getBasicAddress();

        return basicAddress;
    }
}
