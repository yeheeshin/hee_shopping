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
        return "about";
    }

    @GetMapping("/book")
    public String book(Model model) {
        return "ask2";
    }

    @GetMapping("/my")
    public String my(Model model) {
        return "my/mypage";
    }

    @GetMapping("/i_d")
    public String i_d(Model model) {
        return "Item/item_detail";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        return "my/cart";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
