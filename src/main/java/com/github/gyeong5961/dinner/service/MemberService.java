package com.github.gyeong5961.dinner.service;

import com.github.gyeong5961.dinner.dto.Member;
import com.github.gyeong5961.dinner.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;


    public List<Member> findMembers() {
        return memberRepository.findMembers();
    }

    public Member findMember(Long idx) {
        return memberRepository.findByIdx(idx);
    }

    public Member findMember(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    public boolean check(String memberId) {
        return memberRepository.check(memberId);
    }

    public Member updateMember(Member member) {
        return memberRepository.save(member);
    }

    public void login(Member member) {
        memberRepository.save(member);
    }

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        return memberRepository.findByMemberId(memberId);
    }

}

