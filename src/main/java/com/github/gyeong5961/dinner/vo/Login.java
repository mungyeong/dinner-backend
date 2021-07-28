package com.github.gyeong5961.dinner.vo;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    @NotNull(message = "ID 입력부탁드립니다.")
    private String id;
    @NotNull(message = "PASSWORD 입력부탁드립니다.")
    private String password;
}
