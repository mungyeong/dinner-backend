package com.github.gyeong5961.dinner.dto;

import com.fasterxml.jackson.annotation.*;
import com.github.gyeong5961.dinner.entity.BaseEntity;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Where(clause = "is_deleted = 'false'")
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String password;

    @NonNull
    private String email;

    @JsonManagedReference
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "member_map", //조인테이블명
            joinColumns = @JoinColumn(name = "member_id"),  //외래키
            inverseJoinColumns = @JoinColumn(name = "map_id") //반대 엔티티의 외래키
    )
    private List<Map> maps = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Member member = (Member) o;

        return Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return 871316993;
    }
}
