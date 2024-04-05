package com.shopping.hee.service;

import com.shopping.hee.domain.Enum.Role;
import com.shopping.hee.domain.Member;
import com.shopping.hee.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    // 회원가입
    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    // 회원 정보 수정
    public Member memEdit(Long id, Member member) {
        Member byMseq = memberRepository.findByMseq(id);
        byMseq.setName(member.getName());
        byMseq.setPhone(member.getPhone());

        return memberRepository.save(byMseq);
    }

    // 이미 가입된 회원일 경우 예외처리
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());

        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        if("ADMIN".equals(member.getRole())){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.name()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.name()));
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPwd())
                .roles(member.getRole().toString())
                .build();
    }

    // 현재 로그인 한 사람 확인
    public Member nowMember(){
        // 현재 사용자의 정보
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 사용자 아이디를 가져옴
        String userId = authentication.getName();

        if (memberRepository.findByEmail(userId) == null) {
            throw new IllegalStateException("로그인 후 이용해주시길 바랍니다.");
        }

        return memberRepository.findByEmail(userId);
    }

}
