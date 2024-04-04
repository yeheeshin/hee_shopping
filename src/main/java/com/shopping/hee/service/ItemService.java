package com.shopping.hee.service;

import com.shopping.hee.domain.Enum.Category;
import com.shopping.hee.domain.Item;
import com.shopping.hee.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    // 상품 등록
    public Item saveItem(Item item) {
        validateDuplicateItem(item);
        return itemRepository.save(item);
    }

    // id를 이용해서 item 정보 가져오기
    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    // 동일한 상품명이 있을 경우 예외처리
    private void validateDuplicateItem(Item item) {
        Item findItem = itemRepository.findByName(item.getName());

        if (findItem != null) {
            throw new IllegalStateException("동일한 상품명이 있습니다.");
        }
    }

    // 특정 아이템 정보 가져오기
    public List<Item> getOneItem(Long seq) {
        return itemRepository.findByIseq(seq);
    }

    // 카테고리 별 목록 불러오기
    public Page<Item> getItemByCategory(Category category, int page, int size) {
        return itemRepository.findByCategory(category, PageRequest.of(page, size));
    }

    // 종류 별 아이템 검색하기
    public Page<Item> searchItems(Category category, String name, int page, int size) {
        Page<Item> byCategoryAndName = itemRepository.findByCategoryAndNameContaining(category, name, PageRequest.of(page, size));

        if (byCategoryAndName == null) {
            throw new NullPointerException();
        }

        return byCategoryAndName;
    }
}
