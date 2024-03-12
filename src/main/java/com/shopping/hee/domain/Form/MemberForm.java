package com.shopping.hee.domain.Form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "이메일을 입력하세요.")
    private String email;
    @NotEmpty(message = "비밀번호를 입력하세요.")
    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요.")
    private String pwd;
    @NotEmpty(message = "이름을 입력하세요.")
    private String name;
    @NotEmpty(message = "전화번호를 입력하세요.")
    private String phone;
}
