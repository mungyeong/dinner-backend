package com.github.gyeong5961.dinner.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchMap {

    private String id;

    @JsonAlias("place_name")
    private String placeName;

    @JsonAlias("category_name")
    private String categoryName;

    @JsonAlias("category_group_code")
    private String categoryGroupCode;

    @JsonAlias("category_group_name")
    private String categoryGroupName;

    private String phone;

    @JsonAlias("address_name")
    private String addressName;

    @JsonAlias("road_address_name")
    private String roadAddressName;

    private String x;
    private String y;

    @JsonAlias("place_url")
    private String placeUrl;

//    id	String	장소 ID
//    place_name	String	장소명, 업체명
//    category_name	String	카테고리 이름
//    category_group_code	String	중요 카테고리만 그룹핑한 카테고리 그룹 코드
//    category_group_name	String	중요 카테고리만 그룹핑한 카테고리 그룹명
//    phone	String	전화번호
//    address_name	String	전체 지번 주소
//    road_address_name	String	전체 도로명 주소
//    x	String	X 좌표값, 경위도인 경우 longitude (경도)
//    y	String	Y 좌표값, 경위도인 경우 latitude(위도)
//    place_url	String	장소 상세페이지 URL
//    distance	String	중심좌표까지의 거리 (단, x,y 파라미터를 준 경우에만 존재)
//    단위 meter
}
