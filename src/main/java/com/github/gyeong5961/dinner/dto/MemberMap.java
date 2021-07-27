package com.github.gyeong5961.dinner.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MemberMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "member_idx")
    private Member member;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "map_idx")
    private Map map;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MemberMap memberMap = (MemberMap) o;

        if (!Objects.equals(member, memberMap.member)) return false;
        return Objects.equals(map, memberMap.map);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(member);
        result = 31 * result + (Objects.hashCode(map));
        return result;
    }
}
