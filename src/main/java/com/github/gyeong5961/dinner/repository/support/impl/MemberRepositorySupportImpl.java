package com.github.gyeong5961.dinner.repository.support.impl;

import com.github.gyeong5961.dinner.dto.Member;
import com.github.gyeong5961.dinner.repository.support.MemberRepositorySupport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.github.gyeong5961.dinner.dto.QMap.map;
import static com.github.gyeong5961.dinner.dto.QMember.member;
import static com.github.gyeong5961.dinner.dto.QMemberMap.memberMap;

public class MemberRepositorySupportImpl extends QuerydslRepositorySupport implements MemberRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public MemberRepositorySupportImpl(JPAQueryFactory jpaQueryFactory) {
        super(Member.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Member> findMembers() {
        return jpaQueryFactory
                .selectFrom(member)
                .leftJoin(memberMap)
                .on(member.idx.eq(memberMap.member.idx))
                .fetchJoin()
                .leftJoin(map)
                .on(map.idx.eq(memberMap.map.idx))
                .fetchJoin()
                .distinct()
                .orderBy(member.idx.asc())
                .fetch();
    }

    @Override
    public Member findByIdx(Long idx) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.idx.eq(idx))
                .leftJoin(memberMap)
                .on(member.idx.eq(memberMap.member.idx))
                .fetchJoin()
                .leftJoin(map)
                .on(map.idx.eq(memberMap.map.idx))
                .fetchJoin()
                .fetchFirst();
    }

    @Override
    public Member findByMemberId(String memberId) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.MemberId.eq(memberId))
                .leftJoin(memberMap)
                .on(member.idx.eq(memberMap.member.idx))
                .fetchJoin()
                .leftJoin(map)
                .on(map.idx.eq(memberMap.map.idx))
                .fetchJoin()
                .fetchFirst();
    }

}
