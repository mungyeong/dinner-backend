package com.github.gyeong5961.dinner.controller;

import com.github.gyeong5961.dinner.auth.jwt.JwtResponse;
import com.github.gyeong5961.dinner.dto.Member;
import com.github.gyeong5961.dinner.service.AuthService;
import com.github.gyeong5961.dinner.vo.Login;
import com.github.gyeong5961.dinner.vo.SignUpInfo;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/auth", method = RequestMethod.POST)
public class AuthController {

    private final AuthService authservice;

    public AuthController(AuthService authservice) {
        this.authservice = authservice;
    }

    @GetMapping("/check/{memberId}")
    public boolean idCheck(@PathVariable String memberId) {
        return true;
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody Login login) {
        JwtResponse jwtResponse = authservice.login(login);
        Assert.notNull(jwtResponse.getAccessToken(), "맴버 정보가 없습니다..");
        return jwtResponse;
    }

    @PostMapping("/signup")
    public Member signup(@RequestBody @Valid SignUpInfo info) {
        return authservice.addMember(info);
    }
}
