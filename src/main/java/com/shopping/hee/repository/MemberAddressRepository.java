package com.shopping.hee.repository;

import com.shopping.hee.domain.Enum.YesNo;
import com.shopping.hee.domain.Member;
import com.shopping.hee.domain.MemberAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberAddressRepository extends JpaRepository<MemberAddress,Long> {
    // 회원 별 주소지명으로 주소 가져오기
    MemberAddress findByTitleAndMember(String name, Member member);

    // seq로 주소 가져오기
    MemberAddress findByMaseq(Long seq);

    // 회원 별 배송지 목록 가져오기
    List<MemberAddress> findByMember(Member member);

    // 회원별 기본 배송지가 있는지 확인
    MemberAddress findByMemberAndBasic(Member member, YesNo basic);

}
