package com.shopping.hee.repository;

import com.shopping.hee.domain.Enum.Category;
import com.shopping.hee.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // 중복 상품명을 위한 상품명 검색
    Item findByName(String name);

    // 카테고리 별 목록 불러오기
    Page<Item> findByCategory(Category category, Pageable pageable);

    // 특정 아이템 가져오기
    List<Item> findByIseq(Long seq);

    // 종류별 아이템 상품 이름 검색
    Page<Item> findByCategoryAndNameContaining(Category category, String name, Pageable pageable);
}
