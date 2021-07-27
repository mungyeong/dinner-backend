package com.github.gyeong5961.dinner.vo;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpInfo {
    @NotNull
    @Size(max = 13)
    private String id;

    @Size(max = 20)
    private String name;

    @NotNull
    @Size(max = 60)
    private String password;


    @Email(regexp = "", message = "이메일로 입력부탁드립니다.")
    private String email;
}
