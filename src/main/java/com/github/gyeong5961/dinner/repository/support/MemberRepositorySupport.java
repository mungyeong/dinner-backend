package com.github.gyeong5961.dinner.repository.support;

import com.github.gyeong5961.dinner.dto.Member;

import java.util.List;


public interface MemberRepositorySupport {
    List<Member> findMembers();

    Member findByIdx(Long idx);

    Member findByMemberId(String MemberId);

    boolean check(String MemberId);
}
