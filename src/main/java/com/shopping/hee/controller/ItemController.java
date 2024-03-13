package com.shopping.hee.controller;

import com.shopping.hee.domain.Form.ItemForm;
import com.shopping.hee.domain.Item;
import com.shopping.hee.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    // 팔찌
    @GetMapping("Bitem")
    public String getBItems(Model model) {
        List<Item> items = itemService.getBraceletItems();

        model.addAttribute("items", items);
        return "Item/item";
    }

    // 반지
    @GetMapping("Ritem")
    public String getRItems(Model model) {
        List<Item> items = itemService.getRingItems();

        model.addAttribute("items", items);
        return "Item/item";
    }

    // 목걸이
    @GetMapping("Nitem")
    public String getNItems(Model model) {
        List<Item> items = itemService.getNecklaceItems();

        model.addAttribute("items", items);
        return "Item/item";
    }

    // 귀걸이
    @GetMapping("Eitem")
    public String getEItems(Model model) {
        List<Item> items = itemService.getEarringItems();

        model.addAttribute("items", items);
        return "Item/item";
    }


    // 아이템 등록 페이지 이동
    @GetMapping("itemAdd")
    public String itemAddMove(ItemForm item, Model model) {
        model.addAttribute("item", item);

        return "Item/itemAdd";
    }

    @PostMapping("itemAdd")
    public String itemAdd(@ModelAttribute("item") ItemForm item, Model model) {

        try {
            Item item1 = Item.createItem(item);
            itemService.saveItem(item1);

            System.out.println("item = " + item1);

        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "Item/itemAdd";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "Item/itemAdd";
        }

        return "redirect:/Bitem";
    }

}
