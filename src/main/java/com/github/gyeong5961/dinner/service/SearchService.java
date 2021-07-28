package com.github.gyeong5961.dinner.service;

import com.github.gyeong5961.dinner.vo.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class SearchService {

    @Value("${API.REQUEST.URI}")
    private String uri;

    @Value("${API.REQUEST.KEY}")
    private String key;

    private HttpEntity<String> httpEntity;

    @PostConstruct
    protected void init() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + key);
        httpEntity = new HttpEntity<>(headers);
    }


    public Search search(String query, int page) throws IllegalStateException {
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
        Assert.notNull(query,"query");
        ResponseEntity<Search> response = new RestTemplate().exchange(temp, HttpMethod.GET, httpEntity, Search.class);
        return response.getBody();
    }
}
