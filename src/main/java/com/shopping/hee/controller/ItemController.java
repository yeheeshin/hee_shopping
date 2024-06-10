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
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shopping")
public class ItemController {

    private final ItemService itemService;

    // 종류별 아이템 페이지 로드
    @GetMapping("/categoryItem")
    public String getItems(@RequestParam("category") String category, @RequestParam(defaultValue = "0") int page, Model model) {
        int size = 6; // 한 페이지에 보여줄 아이템 갯수
        Page<Item> items = null;

        // category 받아서 카테고리 타입으로 지정해주는 메서드
        Category category1 = itemService.saveCategory(category);

        items = itemService.getItemByCategory(category1, page, size);

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
            Model model, HttpServletRequest request) {
        int size = 6;

        Category category1 = itemService.saveCategory(category);

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

    // 색상 검색
    @PostMapping("/selectCategory")
    @ResponseBody
    public void selectCategory(@RequestBody Map<String, String> requestData, @RequestParam(defaultValue = "0") int page, HttpSession session) {
        int size = 6;

        String category = requestData.get("category");
        System.out.println("category = " + category);

        String selectColor = requestData.get("selectColor");
        System.out.println("selectColor = " + selectColor);

        Category saveCategory = itemService.saveCategory(category);
        ItemColor saveItemColor = itemService.saveItemColor(selectColor);

        Page<Item> items = itemService.selectCategoryColor(saveItemColor, saveCategory, page, size);

        session.setAttribute("items", items);
        session.setAttribute("category", category);
        session.setAttribute("selectColor", selectColor);

    }

    // 색상 검색 후, item 페이지로 이동
    @GetMapping("/getColor")
    public String getColor(HttpSession session, Model model) {
        // 세션 플래시에서 데이터 가져오기
        Page<Item> items = (Page<Item>) session.getAttribute("items");
        String category = (String) session.getAttribute("category");
        String selColor = (String) session.getAttribute("selectColor");

        ItemColor selectColor = ItemColor.valueOf(selColor);

        // 가져온 데이터를 다시 모델에 추가하여 HTML에 전달
        model.addAttribute("items", items);
        model.addAttribute("category", category);
        model.addAttribute("selectColor", selectColor);

        // 세션 지우기
        session.removeAttribute("items");
        session.removeAttribute("category");
        session.removeAttribute("selectColor");

        ItemColor[] itemColors = ItemColor.values();
        model.addAttribute("itemColors", itemColors);

        return "/Item/item";
    }

}
