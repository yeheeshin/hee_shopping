package com.shopping.hee.controller;

import com.shopping.hee.domain.Ask;
import com.shopping.hee.domain.Member;
import com.shopping.hee.service.AskService;
import com.shopping.hee.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shopping")
public class AskController {
    private final AskService askService;
    private final MemberService memberService;

    @GetMapping("/ask")
    public String ask(Ask ask, Model model) {
        model.addAttribute("ask", ask);

        return "ask";
    }

    // 문의 등록
    @PostMapping("/ask")
    public String AskAdd(@ModelAttribute("ask") Ask ask) {
        Member member = memberService.nowMember();
        askService.saveAsk(ask, member);

        return "redirect:/shopping/askCheck";
    }

    // 문의 내역 확인
    @GetMapping("/askCheck")
    public String askCheck(Model model) {
        Member member = memberService.nowMember();
        List<Ask> asks = askService.askMember(member);

        model.addAttribute("asks", asks);

        return "/askCheck";
    }
}
