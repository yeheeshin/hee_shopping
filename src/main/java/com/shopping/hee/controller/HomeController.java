package com.shopping.hee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String main_home(Model model) {

        model.addAttribute("fileName", "ring.jpeg");
        return "/main";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "X/about";
    }

}
