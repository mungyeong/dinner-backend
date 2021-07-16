package com.github.gyeong5961.dinner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchMetaInfo {

    private String[] region;

    private String keyword;

    private String selected_region;

//    region	String[]	질의어에서 인식된 지역의 리스트
//    예: '중앙로 맛집' 에서 중앙로에 해당하는 지역 리스트
//    keyword	String	질의어에서 지역 정보를 제외한 키워드
//    예: '중앙로 맛집' 에서 '맛집'
//    selected_region	String	인식된 지역 리스트 중, 현재 검색에 사용된 지역 정보
}
