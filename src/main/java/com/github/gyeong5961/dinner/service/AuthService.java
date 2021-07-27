package com.github.gyeong5961.dinner.service;

import com.github.gyeong5961.dinner.auth.jwt.JwtResponse;
import com.github.gyeong5961.dinner.auth.jwt.JwtTokenProvider;
import com.github.gyeong5961.dinner.dto.Member;
import com.github.gyeong5961.dinner.vo.Login;
import com.github.gyeong5961.dinner.vo.SignUpInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;

    public JwtResponse login(Login info) {
        Member member = memberService.findMember(info.getId());
        JwtResponse jwtResponse = null;
        if (member != null) {
            if (passwordEncoder.matches(info.getPassword(), member.getPassword())) {
                jwtResponse = jwtTokenProvider.createToken(member);
                memberService.login(member);
            }
        } else {

        }
        return jwtResponse;
    }


    public Member addMember(SignUpInfo info) {
        Member member = Member
                .builder()
                .MemberId(info.getId())
                .name(info.getName())
                .email(info.getEmail())
                .password(passwordEncoder.encode(info.getPassword()))
                .roles("USER")
                .build();
        return memberService.addMember(member);
    }
}
