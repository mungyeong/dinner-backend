package com.github.gyeong5961.dinner.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gyeong5961.dinner.vo.Search;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping(value = "/api/search")
public class SearchController {

    private final ObjectMapper objectMapper;


    @Value("${API.REQUEST.URI}")
    private String uri;

    @Value("${API.REQUEST.KEY}")
    private String key;

    public SearchController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @GetMapping("")
    public Search search(@RequestParam String query, @RequestParam(defaultValue = "1") int page) throws JsonProcessingException {
        URI temp = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("query", query)
                .queryParam("category_group_code", "FD6")
                .queryParam("x", "126.98574")
                .queryParam("y", "37.56943")
                .queryParam("radius", 300)
                .queryParam("size", 10)
                .queryParam("page", page)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + key);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = new RestTemplate().exchange(temp, HttpMethod.GET, httpEntity, String.class);
        return objectMapper.readValue(response.getBody(), Search.class);
    }


//  https://dapi.kakao.com/v2/local/search/keyword.json
//  https://developers.kakao.com/docs/latest/ko/local/dev-guide#search-by-keyword
// query	String	검색을 원하는 질의어	O
// category_group_code	String	카테고리 그룹 코드 결과를 카테고리로 필터링을 원하는 경우 사용	X 음식점: FD6
// x	String	중심 좌표의 X값 혹은 longitude 특정 지역을 중심으로 검색하려고 할 경우 radius와 함께 사용 가능	X
// y	String	중심 좌표의 Y값 혹은 latitude 특정 지역을 중심으로 검색하려고 할 경우 radius와 함께 사용 가능	X
// radius	Integer	중심 좌표부터의 반경거리. 특정 지역을 중심으로 검색하려고 할 경우 중심좌표로 쓰일 x,y와 함께 사용 단위 meter, 0~20000 사이의 값	X
// rect	String	사각형 범위내에서 제한 검색을 위한 좌표. 지도 화면 내 검색시 등 제한 검색에서 사용 가능  좌측 X 좌표,좌측 Y 좌표, 우측 X 좌표, 우측 Y 좌표 형식	X
// page	Integer	결과 페이지 번호 1~45 사이의 값 (기본값: 1)	X
// size	Integer	한 페이지에 보여질 문서의 개수 1~15 사이의 값 (기본값: 15)	X
// sort	String	결과 정렬 순서, distance 정렬을 원할 때는 기준 좌표로 쓰일 x, y와 함께 사용 distance 또는 accuracy (기본값: accuracy)	X
}
