package com.github.gyeong5961.dinner.service;

import com.github.gyeong5961.dinner.auth.jwt.JwtResponse;
import com.github.gyeong5961.dinner.auth.jwt.JwtTokenProvider;
import com.github.gyeong5961.dinner.dto.Member;
import com.github.gyeong5961.dinner.vo.Login;
import com.github.gyeong5961.dinner.vo.SignUpInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;

    public JwtResponse login(Login info) throws IllegalArgumentException {
        Member member = memberService.findMember(info.getId());
        JwtResponse jwtResponse = null;
        Assert.notNull(member, "맴버가 없습니다.");
        Assert.isTrue(passwordEncoder.matches(info.getPassword(), member.getPassword()), "비밀번호가 틀립니다.");
        jwtResponse = jwtTokenProvider.createToken(member);
        memberService.login(member);
        return jwtResponse;
    }


    public Member addMember(SignUpInfo info) {
        Member member = Member
                .builder()
                .MemberId(info.getId())
                .name(info.getName())
                .email(info.getEmail())
                .password(passwordEncoder.encode(info.getPassword()))
                .roles(new HashSet<>(Collections.singleton("USER")))
                .build();
        return memberService.addMember(member);
    }

    public boolean check(String memberId) throws IllegalArgumentException {
        return memberService.check(memberId);
    }
}
