package com.github.gyeong5961.dinner.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Where(clause = "is_deleted = 'false'")
public class Map extends BaseEntity {

    @Id
    @Column(name = "map_id")
    private Long id;

    @JsonAlias("place_name")
    @Column(name = "place_name")
    private String placeName;

    @JsonAlias("category_name")
    @Column(name = "category_name")
    private String categoryName;

    @JsonAlias("category_group_code")
    @Column(name = "category_group_code")
    private String categoryGroupCode;

    @JsonAlias("category_group_name")
    @Column(name = "category_group_name")
    private String categoryGroupName;

    private String phone;

    @JsonAlias("address_name")
    @Column(name = "address_name")
    private String addressName;

    @JsonAlias("road_address_name")
    @Column(name = "road_address_name")
    private String roadAddressName;

    private String x;
    private String y;

    @JsonAlias("place_url")
    @Column(name = "place_url")
    private String placeUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Map map = (Map) o;

        return Objects.equals(id, map.id);
    }

    @Override
    public int hashCode() {
        return 85574506;
    }
}

