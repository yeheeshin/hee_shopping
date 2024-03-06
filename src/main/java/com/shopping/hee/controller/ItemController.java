package com.shopping.hee.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {
    // 팔찌
    @GetMapping("/Bitem")
    public String getBItems(Model model) {
        return "Item/item"; // Thymeleaf 템플릿 이름
    }

    // 반지
    @GetMapping("/Ritem")
    public String getRItems(Model model) {
        return "Item/item"; // Thymeleaf 템플릿 이름
    }

    // 목걸이
    @GetMapping("/Nitem")
    public String getNItems(Model model) {
        return "Item/item"; // Thymeleaf 템플릿 이름
    }

    // 귀걸이
    @GetMapping("/Eitem")
    public String getEItems(Model model) {
        return "Item/item"; // Thymeleaf 템플릿 이름
    }
}
