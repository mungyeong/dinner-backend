package com.github.gyeong5961.dinner.controller;

import com.github.gyeong5961.dinner.dto.Member;
import com.github.gyeong5961.dinner.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("")
    public List<Member> findMembers() {
        return memberService.findMembers();
    }

    @GetMapping("/{idx}")
    public Member findMember(@PathVariable Long idx) {
        return memberService.findMember(idx);
    }

    @PutMapping("")
    public Member updateMember(@RequestBody @Valid Member info) {
        return memberService.updateMember(info);
    }

}
