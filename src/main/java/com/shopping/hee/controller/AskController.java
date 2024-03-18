package com.shopping.hee.controller;

import com.shopping.hee.domain.Ask;
import com.shopping.hee.service.AskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AskController {
    private final AskService askService;

    @GetMapping("/ask")
    public String ask(Ask ask, Model model) {
        model.addAttribute("ask", ask);

        return "ask";
    }

    // 문의 등록
    @PostMapping("/ask")
    public String AskAdd(@ModelAttribute("ask") Ask ask) {
        askService.saveAsk(ask);

        return "askCheck";
    }

    // 문의 내역 확인

}
