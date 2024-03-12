package com.shopping.hee.controller;
import com.shopping.hee.domain.Category;
import com.shopping.hee.domain.Form.ItemForm;
import com.shopping.hee.domain.Item;
import com.shopping.hee.domain.ItemColor;
import com.shopping.hee.service.ItemService;
import com.shopping.hee.utils.MyPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

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
    public String itemAddMove(ItemForm item, Model model) {
        model.addAttribute("item", item);

        return "itemAdd";
    }

    @PostMapping("itemAdd")
    public String itemAdd(@ModelAttribute("item") ItemForm item, Model model) {
        System.out.println("여기를 지나는가 확인");

        try {
            System.out.println("여기를 지나는가 확인2");
            Item item1 = Item.createItem(item);
            itemService.saveItem(item1);

            System.out.println("item = " + item1);

        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "itemAdd";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "itemAdd";
        }

        return "redirect:/Bitem";
    }

}
