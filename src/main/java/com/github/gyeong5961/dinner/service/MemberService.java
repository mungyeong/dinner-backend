package com.github.gyeong5961.dinner.service;

import com.github.gyeong5961.dinner.dto.Member;
import com.github.gyeong5961.dinner.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }


    public Member insert(Member maps) {
        return memberRepository.saveAndFlush(maps);
    }

    public Member update(Member maps) {
        return memberRepository.saveAndFlush(maps);
    }

    public Member find(Long id) {
        return memberRepository.findById(id).orElse(new Member());
    }

    public void delete(Long id) {
        Member member = memberRepository.findById(id).orElse(new Member());
        member.setDeleted(Boolean.TRUE);
        memberRepository.save(member);
    }

    public void delete(List<Member> members) {
        for(int i=0;i<members.size();i++){
            Member member = members.get(0);
            System.out.println(member.getId());
            member.setDeleted(Boolean.TRUE);
        }
        memberRepository.saveAll(members);
    }
}
