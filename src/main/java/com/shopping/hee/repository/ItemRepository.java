package com.shopping.hee.repository;

import com.shopping.hee.domain.Enum.Category;
import com.shopping.hee.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // 중복 상품명을 위한 상품명 검색
    Item findByName(String name);

    // 카테고리 별 목록 불러오기
    List<Item> findByCategory(Category category);

    // 특정 아이템 가져오기
    List<Item> findByIseq(Long seq);
}
