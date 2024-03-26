package com.shopping.hee.repository;

import com.shopping.hee.domain.Member;
import com.shopping.hee.domain.MemberAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberAddressRepository extends JpaRepository<MemberAddress,Long> {
    // 주소지명으로 주소 가져오기
    MemberAddress findByTitle(String name);

    // 회원 별 배송지 목록 가져오기
    List<MemberAddress> findByMember(Member member);
}
