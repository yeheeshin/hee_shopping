package com.shopping.hee.service;

import com.shopping.hee.domain.Item;
import com.shopping.hee.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 동일한 상품명이 있을 경우 예외처리
    private void validateDuplicateItem(Item item) {
        Item findItem = itemRepository.findByName(item.getName());

        if (findItem != null) {
            throw new IllegalStateException("동일한 상품명이 있습니다.");
        }
    }
}
