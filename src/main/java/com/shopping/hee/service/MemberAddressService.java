package com.shopping.hee.service;

import com.shopping.hee.domain.Item;
import com.shopping.hee.domain.MemberAddress;
import com.shopping.hee.repository.MemberAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberAddressService {
    private final MemberAddressRepository memberAddressRepository;

    // 배송지 추가
    public MemberAddress save(MemberAddress memberAddress) {
        validate(memberAddress.getTitle());

        return memberAddressRepository.save(memberAddress);
    }


    // 동일한 주소지명 오류 발생
    public void validate(String title) {
        MemberAddress findAddress = memberAddressRepository.findByTitle(title);

        if (findAddress != null) {
            throw new IllegalStateException("주소지명이 중복됩니다.");
        }
    }

}
