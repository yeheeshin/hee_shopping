package com.shopping.hee.controller;

import com.shopping.hee.domain.Enum.Category;
import com.shopping.hee.domain.Enum.ItemColor;
import com.shopping.hee.domain.Form.ItemForm;
import com.shopping.hee.domain.Item;
import com.shopping.hee.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shopping")
public class ItemController {

    private final ItemService itemService;

    // 종류별 아이템 페이지 로드
    @GetMapping("/categoryItem")
    public String getItems(@RequestParam("category") String category, @RequestParam(defaultValue = "0") int page, Model model) {
        int size = 2; // 한 페이지에 보여줄 아이템 갯수
        Page<Item> items = null;
        switch (category) {
            case "ring":
                items = itemService.getItemByCategory(Category.Ring, page, size);
                break;
            case "bra":
                items = itemService.getItemByCategory(Category.Bracelet, page, size);
                break;
            case "neck":
                items = itemService.getItemByCategory(Category.Necklace, page, size);
                break;
            case "ear":
                items = itemService.getItemByCategory(Category.Earring, page, size);
                break;
        }
        ItemColor[] itemColors = ItemColor.values();

        model.addAttribute("itemColors", itemColors);
        model.addAttribute("items", items);
        model.addAttribute("category", category);

        return "Item/item";
    }


    // 아이템 등록 페이지 이동
    @GetMapping("/itemAdd")
    public String itemAddMove(ItemForm item, Model model) {
        model.addAttribute("item", item);

        return "Item/itemAdd";
    }

    // 아이템 등록
    @PostMapping("/itemAdd")
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

    // 아이템 상세 보기
    @GetMapping("/i_d")
    public String itemDetail(@RequestParam("id") Long seq, Model model) {
        List<Item> items = itemService.getOneItem(seq);
        model.addAttribute("items", items);

        return "Item/item_detail";
    }

    // 아이템 검색 하기
    @GetMapping("/search")
    public String searchItem(
            @RequestParam("category") String category, @RequestParam("keyword") String keyword, @RequestParam(defaultValue = "0") int page,
            Model model, HttpServletRequest request)
    {
        int size = 2;
        Category category1 = null;

        switch (category) {
            case "ring":
                category1 = Category.Ring; 
                break;
            case "bra":
                category1 = Category.Bracelet; 
                break;
            case "neck":
                category1 = Category.Necklace; 
                break;
            case "ear":
                category1 = Category.Earring; 
                break;
        }

        String prevUrl = request.getHeader("referer");

        HttpSession session = request.getSession();
        session.setAttribute("prevUrl", prevUrl);

        Page<Item> items = itemService.searchItems(category1, keyword, page, size);

        ItemColor[] itemColors = ItemColor.values();

        model.addAttribute("itemColors", itemColors);
        model.addAttribute("items", items);
        model.addAttribute("category", category);
        model.addAttribute("keyword", keyword);

        return "Item/itemSearch";
    }
}
