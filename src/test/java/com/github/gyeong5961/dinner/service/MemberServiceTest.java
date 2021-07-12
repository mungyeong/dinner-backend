package com.github.gyeong5961.dinner.service;

import com.github.gyeong5961.dinner.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    @Test
    void test1() {
        memberRepository.findAll().forEach(System.out::println);
    }

}