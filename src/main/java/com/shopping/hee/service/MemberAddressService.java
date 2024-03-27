package com.shopping.hee.service;

import com.shopping.hee.domain.Enum.YesNo;
import com.shopping.hee.domain.Item;
import com.shopping.hee.domain.Member;
import com.shopping.hee.domain.MemberAddress;
import com.shopping.hee.repository.MemberAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberAddressService {
    private final MemberAddressRepository memberAddressRepository;
    private final MemberService memberService;

    // 배송지 추가
    public MemberAddress save(MemberAddress memberAddress) {
        validate(memberAddress.getTitle());

        return memberAddressRepository.save(memberAddress);
    }

    // 현재 로그인 한 회원의 배송지 목록
    public List<MemberAddress> memberAddressList(Member member) {
        return memberAddressRepository.findByMember(member);
    }

    // 동일한 주소지명 오류 발생
    public void validate(String title) {
        Member member = memberService.nowMember();

        MemberAddress findAddress = memberAddressRepository.findByTitleAndMember(title, member);

        if (findAddress != null) {
            throw new IllegalStateException("주소지명이 중복됩니다.");
        }
    }

    // 회원이 처음 배송지를 등록한 경우
    public boolean firstAddress(MemberAddress memberAddress) {
        List<MemberAddress> byMember = memberAddressRepository.findByMember(memberAddress.getMember());

        if (byMember.isEmpty()) {
            memberAddress.setBasic(YesNo.YES);
            return true;
        } else {
            memberAddress.setBasic(YesNo.NO);
            return false;
        }

    }

    // 기본 배송지 변경
    public void defaultAddress(Long seq) {
        Member member = memberService.nowMember();
        // 기존의 기본 배송지 가져오기
        MemberAddress ma1 = memberAddressRepository.findByMemberAndBasic(member, YesNo.YES);
        if (ma1 != null) {
            ma1.setBasic(YesNo.NO);
            MemberAddress memberAddress = memberAddressRepository.findByMaseq(seq);
            memberAddress.setBasic(YesNo.YES);
        }
    }

}
