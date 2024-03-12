package com.shopping.hee.controller;
import com.shopping.hee.domain.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {
    // 팔찌
    @GetMapping("Bitem")
    public String getBItems(Model model) {
        return "Item/item"; // Thymeleaf 템플릿 이름
    }

    // 반지
    @GetMapping("Ritem")
    public String getRItems(Model model) {
        return "Item/item"; // Thymeleaf 템플릿 이름
    }

    // 목걸이
    @GetMapping("Nitem")
    public String getNItems(Model model) {
        return "Item/item"; // Thymeleaf 템플릿 이름
    }

    // 귀걸이
    @GetMapping("Eitem")
    public String getEItems(Model model) {
        return "Item/item"; // Thymeleaf 템플릿 이름
    }


    // 아이템 등록 페이지 이동
    @GetMapping("itemAdd")
    public String itemAddMove(Item item, Model model) {
        model.addAttribute("item", item);
        return "ItemAdd";
    }

    @PostMapping("itemAdd")
    public String itemAdd() {
        return "redirect:/";
    }

}
