package com.github.gyeong5961.dinner.repository;

import com.github.gyeong5961.dinner.dto.Map;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapRepository extends JpaRepository<Map, Long> {
    @Override
    @Query(
            "select m from Map m left join fetch m.members"
    )
    List<Map> findAll();

    @Query(
            "select m from Map m left join fetch m.members"
    )
    @Where(clause = "")
    List<Map> findAllAdmin();
}
