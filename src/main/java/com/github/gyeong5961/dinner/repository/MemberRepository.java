package com.github.gyeong5961.dinner.repository;

import com.github.gyeong5961.dinner.dto.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Long> {
    @Override
    @Query(
            "select m from Member m left join fetch m.maps"
    )
    List<Member> findAll();

}
