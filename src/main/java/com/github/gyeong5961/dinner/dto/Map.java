package com.github.gyeong5961.dinner.dto;

import com.github.gyeong5961.dinner.entity.BaseEntity;
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
@AllArgsConstructor
@Builder
@Entity
public class Map extends BaseEntity {

    @Id
    @Column(name = "map_id")
    private Long id;

    private String place_name;
    private String category_name;
    private String category_group_code;
    private String category_group_name;
    private String phone;
    private String address_name;
    private String road_address_name;
    private String x;
    private String y;
    private String place_url;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "member_map", //조인테이블명
            joinColumns = @JoinColumn(name = "map_id"),  //외래키
            inverseJoinColumns = @JoinColumn(name = "member_id") //반대 엔티티의 외래키
    )
    @ToString.Exclude
    private List<Member> members;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Map maps = (Map) o;

        return Objects.equals(id, maps.id);
    }

    @Override
    public int hashCode() {
        return 85574506;
    }
}

