package com.github.gyeong5961.dinner.controller;

import com.github.gyeong5961.dinner.auth.jwt.JwtResponse;
import com.github.gyeong5961.dinner.dto.Member;
import com.github.gyeong5961.dinner.service.AuthService;
import com.github.gyeong5961.dinner.vo.Login;
import com.github.gyeong5961.dinner.vo.SignUpInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/auth", method = RequestMethod.POST)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authservice;

    @GetMapping("/check/{memberId}")
    public boolean idCheck(@PathVariable String memberId) {
        return authservice.check(memberId);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody @Valid Login login) {
        JwtResponse jwtResponse = authservice.login(login);
        Assert.notNull(jwtResponse.getAccessToken(), "해당 맴버가 없습니다.");
        return jwtResponse;
    }

    @PostMapping("/signup")
    public Member signup(@RequestBody @Valid SignUpInfo info) {
        return authservice.addMember(info);
    }
}
