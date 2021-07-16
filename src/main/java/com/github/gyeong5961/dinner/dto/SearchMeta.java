package com.github.gyeong5961.dinner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchMeta {

    private long total_count;

    private long pageable_count;

    private boolean is_end;

    private SearchMetaInfo same_name;
//    Name	Type	Description
//    total_count	Integer	검색어에 검색된 문서 수
//    pageable_count	Integer	total_count 중 노출 가능 문서 수 (최대값: 45)
//    is_end	Boolean	현재 페이지가 마지막 페이지인지 여부
//    값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음
//    same_name	RegionInfo	질의어의 지역 및 키워드 분석 정보

}
