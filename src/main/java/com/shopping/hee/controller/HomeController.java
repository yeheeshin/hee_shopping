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
    @GetMapping("/a_item")
    public String a_item(Model model) {
        return "Item/apple_item";
    }

    @GetMapping("/s_item")
    public String s_item(Model model) {
        return "Item/samsung_item";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/book")
    public String book(Model model) {
        return "ask";
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

}
