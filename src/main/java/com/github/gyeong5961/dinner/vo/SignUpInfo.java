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
    @Size(min = 6, max = 13, message = "name 길이는 6~13자입니다.")
    private String id;

    @NotNull
    @Size(max = 20, message = "name 길이는 1~20자입니다.")
    private String name;

    @NotNull(message = "이메일로 입력부탁드립니다.")
    @Size(min = 10, max = 60, message = "password 길이는 10~60자입니다,")
    private String password;

    @NotNull(message = "이메일로 입력부탁드립니다.")
    @Email(regexp = "", message = "이메일로 입력부탁드립니다.")
    private String email;
}
