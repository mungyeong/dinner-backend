package com.github.gyeong5961.dinner.repository;

import com.github.gyeong5961.dinner.dto.Member;
import com.github.gyeong5961.dinner.repository.support.MemberRepositorySupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositorySupport {

}
