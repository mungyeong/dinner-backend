package com.github.gyeong5961.dinner.repository.support.impl;

import com.github.gyeong5961.dinner.dto.Map;
import com.github.gyeong5961.dinner.repository.support.MapRepositorySupport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class MapRepositorySupportImpl extends QuerydslRepositorySupport implements MapRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public MapRepositorySupportImpl(JPAQueryFactory jpaQueryFactory) {
        super(Map.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

}
