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
}