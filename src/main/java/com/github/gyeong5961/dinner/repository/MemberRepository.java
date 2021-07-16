package com.github.gyeong5961.dinner.repository;

import com.github.gyeong5961.dinner.dto.Member;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Override
    @Query(
            "select m from Member m left join fetch m.maps"
    )
    List<Member> findAll();

    @Query(
            "select m from Member m left join fetch m.maps"
    )
    @Where(clause = "")
    List<Member> findAllAdmin();

    Member findByName(String name);

}
