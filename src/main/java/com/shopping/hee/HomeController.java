package com.shopping.hee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/menu")
    public String menu(Model model) {
        return "menu";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/book")
    public String book(Model model) {
        return "book";
    }

    @GetMapping("/my")
    public String my(Model model) {
        return "mypage";
    }

}
