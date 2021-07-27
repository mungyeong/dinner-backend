package com.github.gyeong5961.dinner.auth.jwt;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
}
