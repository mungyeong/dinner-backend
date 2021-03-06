package com.github.gyeong5961.dinner.vo;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

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
