package com.shopping.hee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String main_home(Model model) {
        return "main";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "main";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "X/about";
    }

    @GetMapping("/askCheck")
    public String askCheck(Model model) {
        return "askCheck";
    }

    @GetMapping("/my")
    public String my(Model model) {
        return "my/mypage";
    }


}
