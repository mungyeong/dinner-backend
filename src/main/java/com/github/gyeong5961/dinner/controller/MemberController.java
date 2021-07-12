package com.github.gyeong5961.dinner.controller;

import com.github.gyeong5961.dinner.dto.Member;
import com.github.gyeong5961.dinner.service.MemberService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;


@RestController
@EnableWebMvc
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    private final Gson gson;

    public MemberController(MemberService memberService, Gson gson) {
        this.memberService = memberService;
        this.gson = gson;
    }


    @GetMapping(value = "/{id}")
    public String getMember(@PathVariable(value = "id") Long id) {
        return gson.toJson(memberService.find(id));
    }

    @GetMapping(path = "/")
    public String getAllMember() {
        return gson.toJson(memberService.findAll());
    }

    @PostMapping(path = "/")
    public String postMember(@RequestBody String info) {
        Member member = gson.fromJson(info, Member.class);
        return gson.toJson(memberService.insert(member));
    }

    @PutMapping(value = "/{id}")
    public String putMember(@Validated Member member, @PathVariable(value = "id") Long id) {

        return gson.toJson(memberService.update(member));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMember(@PathVariable(value = "id") Long id) {
        memberService.delete(id);
    }

    @DeleteMapping(value = "/")
    public void deleteMembers(@RequestBody String list) {
        List<Member> members = gson.fromJson(list,new TypeToken<List<Member>>(){}.getType());
        memberService.delete(members);
    }

}
