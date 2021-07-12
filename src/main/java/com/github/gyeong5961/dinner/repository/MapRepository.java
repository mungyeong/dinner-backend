package com.github.gyeong5961.dinner.repository;

import com.github.gyeong5961.dinner.dto.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MapRepository extends JpaRepository<Map, Long> {
    @Override
    @Query(
            "select m from Map m left join fetch m.members"
    )
    List<Map> findAll();

}
