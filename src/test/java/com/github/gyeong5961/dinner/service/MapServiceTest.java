package com.github.gyeong5961.dinner.service;

import com.github.gyeong5961.dinner.repository.MapRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MapServiceTest {

    @Autowired
    MapRepository mapRepository;

    @Test
    void test1() {
        mapRepository.findAll().forEach(System.out::println);
    }
}