package com.github.gyeong5961.dinner.dto;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Search {


    @JsonAlias("meta")
    private SearchMeta searchMeta;

    @JsonAlias("documents")
    private List<SearchMap> searchMap;
}
