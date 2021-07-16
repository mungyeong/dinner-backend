package com.github.gyeong5961.dinner.repository;

import com.github.gyeong5961.dinner.dto.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapRepository extends JpaRepository<Map, Long> {
}
