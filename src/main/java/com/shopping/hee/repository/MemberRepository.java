package com.shopping.hee.repository;

import com.shopping.hee.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 중복 회원 검증을 위한 이메일 검색
    Member findByEmail(String email);

    // seq 로 회원 검색
    Member findByMseq(Long seq);
}
