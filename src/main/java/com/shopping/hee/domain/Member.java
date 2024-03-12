package com.shopping.hee.domain;

import com.shopping.hee.domain.Form.MemberForm;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mSeq;

    private String email;
    private String pwd;
    private String name;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private YesNo deleteStatus;

    public static Member createMember(MemberForm memberForm, PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.setName(memberForm.getName());
        member.setEmail(memberForm.getEmail());
        member.setPhone(memberForm.getPhone());

        String pwd = passwordEncoder.encode(memberForm.getPwd());
        member.setPwd(pwd);
        member.setRole(Role.USER);
        member.setDeleteStatus(YesNo.NO);

        return member;
    }
}