package com.shopping.hee.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {
    @GetMapping("/item")
    public String getItems(Model model) {
        return "Item/item"; // Thymeleaf 템플릿 이름
    }
}
