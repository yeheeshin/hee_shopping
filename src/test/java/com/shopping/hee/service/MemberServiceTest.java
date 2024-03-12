package com.shopping.hee.service;

import com.shopping.hee.domain.Member;
import com.shopping.hee.domain.Form.MemberForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember() {
        MemberForm memberForm = new MemberForm();
        memberForm.setName("신예희");
        memberForm.setEmail("test@naver.com");
        memberForm.setPhone("01023074754");
        memberForm.setPwd("123456");
        return Member.createMember(memberForm, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    void saveMember() {
        Member user = createMember();
        Member savedUser = memberService.saveMember(user);

        assertEquals(user.getName(), savedUser.getName());
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getPhone(), savedUser.getPhone());
        assertEquals(user.getPwd(), savedUser.getPwd());
        assertEquals(user.getRole(), savedUser.getRole());
        System.out.println("@@@@" + savedUser);
    }
}