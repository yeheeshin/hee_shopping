package com.shopping.hee.controller;

import com.shopping.hee.domain.Member;
import com.shopping.hee.domain.MemberForm;
import com.shopping.hee.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("join")
    public String memberForm(MemberForm memberForm, Model model) {
        model.addAttribute("member", memberForm);
        return "member/join";
    }

    @GetMapping("login")
    public String memberForm2(MemberForm memberForm, Model model) {
        model.addAttribute("member", memberForm);
        return "member/login";
    }


    // 회원가입
    @PostMapping("join")
    public String memberJoin(@Valid @ModelAttribute("member") MemberForm memberForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "member/join";
        }
        try {
            Member member = Member.createMember(memberForm, passwordEncoder);
            memberService.saveMember(member);
            System.out.println("저장이 잘 되는가? " + member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/join";
        }
        return "redirect:/";
    }
}
