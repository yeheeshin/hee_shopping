package com.shopping.hee.controller;

import com.shopping.hee.domain.Enum.YesNo;
import com.shopping.hee.domain.Member;
import com.shopping.hee.domain.Form.MemberForm;
import com.shopping.hee.domain.MemberAddress;
import com.shopping.hee.service.MemberAddressService;
import com.shopping.hee.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final MemberAddressService memberAddressService;

    @GetMapping("/join")
    public String memberJoin(MemberForm memberForm, Model model) {
        model.addAttribute("member", memberForm);
        return "member/join";
    }

    @GetMapping("/login")
    public String memberLogin() {
        return "member/login";
    }

    // 로그인 에러
    @GetMapping("/loginError")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "member/login";
    }

    // 회원가입
    @PostMapping("/join")
    public String memberJoin(@Valid @ModelAttribute("member") MemberForm memberForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "member/join";
        }
        try {
            System.out.println("여기니?");
            Member member = Member.createMember(memberForm, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/join";
        }
        return "member/login";
    }

    // 마이페이지 이동
    @GetMapping("/my")
    public String my(Model model) {
        Member nowMember = memberService.nowMember();
        model.addAttribute("nowMember", nowMember);

        return "my/mypage";
    }

    // 배송지 관리 페이지 이동
    @GetMapping("/myadd")
    public String myAdd(Model model) {
        Member member = memberService.nowMember();
        List<MemberAddress> list = memberAddressService.memberAddressList(member);

        model.addAttribute("list", list);
        return "my/myAddressList";
    }

    // 주문 목록 페이지 이동
    @GetMapping("/o_l")
    public String orderList(Model model) {
        return "my/orderList";
    }

    // 배송지 관리 페이지 이동
    @GetMapping("/memEdit")
    public String memberEdit(Model model) {
        return "my/memberEdit";
    }

    // 배송지 추가 페이지로 이동
    @GetMapping("/myAddress")
    public String myAddress(MemberAddress memberAddress, Model model) {
        model.addAttribute("memberAddress", memberAddress);

        return "my/addressAdd";
    }

    // 배송지 추가
    @PostMapping("/myAddress")
    public String addAddress(@ModelAttribute("memberAddress") MemberAddress memberAddress, @RequestParam("yesno") String yesno, Model model) {
        try {
            Member member = memberService.nowMember();
            memberAddress.setMember(member);

            memberAddressService.save(memberAddress);
            memberAddressService.firstAddress(memberAddress);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "my/addressAdd";
        }

        return "redirect:/member/myadd";
    }
}