package com.github.gyeong5961.dinner.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gyeong5961.dinner.dto.Member;
import com.github.gyeong5961.dinner.service.MapService;
import com.github.gyeong5961.dinner.service.MemberService;
import com.github.gyeong5961.dinner.vo.SignUpInfo;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/api/member")
public class MemberController {

    private final MemberService memberService;

    private final MapService mapService;

    private final ObjectMapper mapper;

    public MemberController(MemberService memberService, MapService mapService, ObjectMapper mapper) {
        this.memberService = memberService;
        this.mapService = mapService;
        this.mapper = mapper;
    }

    @GetMapping("")
    public List<Member> findMembers() {
        return memberService.findMembers();
    }

    @GetMapping("/{idx}")
    public Member findMember(@PathVariable Long idx) {
        return memberService.findMember(idx);
    }

    @PutMapping("")
    public Member updateMember(@RequestBody Member info) {
        return memberService.updateMember(info);
    }

}
