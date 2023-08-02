package com.shopping.hee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        return "index";
    }
    @GetMapping("/item")
    public String item(Model model) {
        return "Item/apple_item";
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

    @GetMapping("/i_d")
    public String i_d(Model model) {
        return "Item/item_detail";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        return "cart";
    }

}
