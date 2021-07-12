package com.github.gyeong5961.dinner.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
@Where(clause = "is_deleted = 'false'")
public class BaseEntity {

    @Setter
    @Column(columnDefinition="boolean default false")
    private boolean isDeleted;

    @CreatedDate
    @Column(updatable = false,columnDefinition = "DATETIME DEFAULT now()")
    private LocalDateTime created_at;

    @LastModifiedDate
    @Column(columnDefinition = "DATETIME DEFAULT now()")
    private LocalDateTime updated_at;
}
