package com.github.gyeong5961.dinner.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Where(clause = "is_deleted = 'false'")
public class Member extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @NotNull
    @Size(max = 13)
    @Column(name = "id", unique = true, length = 40)
    private String MemberId;

    @NotNull
    @Size(max = 20)
    @Column(length = 60)
    private String name;


    @NotNull
    @ToString.Exclude
    @JsonIgnore
    @Size(max = 60)
    @Column(length = 180)
    private String password;

    @NotNull
    @Email(regexp = "", message = "이메일로 입력부탁드립니다.")
    @Column(length = 100)
    private String email;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;


    @JsonManagedReference
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<MemberMap> mapList;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime lastLoginAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Member member = (Member) o;

        return Objects.equals(idx, member.idx);
    }

    @Override
    public int hashCode() {
        return 871316993;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roleList = new HashSet<>();
        for (String role: this.roles){
            roleList.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return roleList;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return MemberId;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return !isEnabled();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !isEnabled();
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return !isEnabled();
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return !isDeleted();
    }
}
