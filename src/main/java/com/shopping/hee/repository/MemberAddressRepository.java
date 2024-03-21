package com.shopping.hee.repository;

import com.shopping.hee.domain.MemberAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAddressRepository extends JpaRepository<MemberAddress,Long> {
    // 주소지명으로 주소 가져오기
    MemberAddress findByTitle(String name);
}
