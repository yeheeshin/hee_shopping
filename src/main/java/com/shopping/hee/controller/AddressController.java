package com.shopping.hee.controller;

import com.shopping.hee.domain.Member;
import com.shopping.hee.domain.MemberAddress;
import com.shopping.hee.service.MemberAddressService;
import com.shopping.hee.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class AddressController {
    private final MemberAddressService memberAddressService;
    private final MemberService memberService;

    // 배송지 추가 페이지로 이동
    @GetMapping("/myAddress")
    public String myAddress(MemberAddress memberAddress, Model model) {
        model.addAttribute("memberAddress", memberAddress);

        return "my/addressAdd";
    }

    // 배송지 추가
    @PostMapping("/myAddress")
    public String addAddress(@ModelAttribute("memberAddress") MemberAddress memberAddress, Model model) {
        try {
            Member member = memberService.nowMember();
            memberAddress.setMember(member);

            memberAddressService.firstAddress(memberAddress);
            memberAddressService.save(memberAddress);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "my/addressAdd";
        }

        return "redirect:/member/myadd";
    }

    // 기본 배송지 변경
    @GetMapping("/default")
    public String DefaultAddress(@RequestParam("id") Long id) {
        memberAddressService.defaultAddress(id);
        return "redirect:/member/myadd";
    }

    // 배송지 관리 페이지 이동
    @GetMapping("/myadd")
    public String myAdd(Model model) {
        Member member = memberService.nowMember();
        List<MemberAddress> list = memberAddressService.memberAddressList(member);

        model.addAttribute("list", list);
        return "my/myAddressList";
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
