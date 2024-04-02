package com.shopping.hee.repository;

import com.shopping.hee.domain.Ask;
import com.shopping.hee.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AskRepository extends JpaRepository<Ask, Long> {
    // 회원 별 문의 내역 조회
    List<Ask> findByMember(Member member);

    // seq로 문의 상세 정보 조회
    Ask findByAseq(Long seq);
}
