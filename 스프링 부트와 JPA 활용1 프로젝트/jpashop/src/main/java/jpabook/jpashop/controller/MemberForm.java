package jpabook.jpashop.controller;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberForm {

    @NotBlank(message = "회원명은 필수 입력 값입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
