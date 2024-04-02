package com.shopping.hee.controller;

import com.shopping.hee.domain.*;
import com.shopping.hee.domain.Form.OrderForm;
import com.shopping.hee.domain.Form.OrderItemsForm;
import com.shopping.hee.domain.Form.OrderListForm;
import com.shopping.hee.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shopping") // 추후에 order 로 바꾸기
public class OrderController {

    private final ItemService itemService;
    private final MemberService memberService;
    private final MemberAddressService memberAddressService;
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

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

    // 주문하기
    @PostMapping("/orders")
    public String buyItems(@ModelAttribute OrderListForm orderListForm, @ModelAttribute("address") Address address, @RequestParam("totalPrice") int totalPrice) {
        Member member = memberService.nowMember();

        List<OrderForm> forms = orderListForm.getForms();

        Long iseq = forms.get(0).getIseq();
        Item item = itemService.findById(iseq);

        try {
            Orders orders = Orders.createOrder(address, totalPrice, item, member);
            orderService.saveOrder(orders);
            if (forms != null && !forms.isEmpty()) {
                for (OrderForm form : forms) {
                    Item detailItem = itemService.findById(form.getIseq());

                    OrderDetail od1 = OrderForm.createOrderDetail(detailItem, form.getCount(), orders);
                    orderDetailService.saveOrderItem(od1);
                    orderService.deleteCartItem(member, detailItem);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "/main";
    }

    // 주문 상세 불러오기
    @GetMapping("/orderDetail")
    public String orderDetail(@RequestParam("id") Long id, Model model) {
        Orders orderDetail = orderService.findOrderDetail(id);
        List<OrderDetail> orderItemDetail = orderDetailService.findOrderDetail(orderDetail);

        model.addAttribute("orderDetail", orderDetail);
        model.addAttribute("orderItemDetail", orderItemDetail);
        return "/my/orderDetail";
    }
}
