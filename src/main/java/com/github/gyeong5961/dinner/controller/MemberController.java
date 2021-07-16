package com.github.gyeong5961.dinner.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gyeong5961.dinner.dto.Map;
import com.github.gyeong5961.dinner.dto.Member;
import com.github.gyeong5961.dinner.service.MapService;
import com.github.gyeong5961.dinner.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    private final MapService mapService;

    private final ObjectMapper mapper = new ObjectMapper();

    public MemberController(MemberService memberService, MapService mapService) {
        this.memberService = memberService;
        this.mapService = mapService;
    }


    @GetMapping(value = "/{id}")
    public Member getMember(@PathVariable Long id){
        return memberService.find(id);
    }

    @GetMapping
    public List<Member> getAllMember(){
        return memberService.findAll();
    }

    @PostMapping
    public Member postMember(@RequestBody String info) throws JsonProcessingException {
        Member member = mapper.readValue(info, Member.class);
        return memberService.insert(member);
    }

    @PutMapping(value = "/{id}")
    public Member putMember(@RequestBody String info, @PathVariable Long id) throws JsonProcessingException {
        Member member = mapper.readValue(info, Member.class);
        member.setId(id);
        return memberService.update(member);
    }

    @PutMapping
    public Member putMembers(@RequestBody String info) throws JsonProcessingException {
        Member member = mapper.readValue(info, Member.class);
        return memberService.update(member);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.delete(id);
    }

    @DeleteMapping
    public void deleteMembers(@RequestBody String infos) throws JsonProcessingException {
        List<Member> members = mapper.readValue(infos, new TypeReference<List<Member>>() {
        });
        memberService.delete(members);
    }

    @PutMapping(value = "/{member_id}/{map_id}")
    public Member addMap(
            @PathVariable Long member_id,
            @PathVariable Long map_id) throws JsonProcessingException {
        Member member = memberService.find(member_id);
        Map map = mapService.find(map_id);
        List<Map> maps = member.getMaps();
        if (!maps.contains(map)) {
            member.getMaps().add(map);
        }
        member = memberService.update(member);
        return member;
    }

}
