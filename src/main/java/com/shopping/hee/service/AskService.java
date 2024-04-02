package com.shopping.hee.service;

import com.shopping.hee.domain.Ask;
import com.shopping.hee.domain.Member;
import com.shopping.hee.repository.AskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AskService {
    private final AskRepository askRepository;

    public Ask saveAsk(Ask ask, Member member) {
        ask.setAdate(LocalDate.now());
        ask.setMember(member);

        return askRepository.save(ask);
    }

    // 회원 별 문의 내역 조회
    public List<Ask> askMember(Member member) {
        return askRepository.findByMember(member);
    }
}
