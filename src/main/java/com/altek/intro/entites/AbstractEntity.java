package com.altek.intro.entites;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Access(AccessType.PROPERTY)
public abstract class AbstractEntity implements Serializable {

    private Long id;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

//    public void setId(final Long id) {
//        this.id = id;
//    }

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "CREATED_BY", nullable = true)
    @CreatedBy
    private String createdBy;

    private Date createdTime;
    @Column(name = "CREATED_TIME", nullable = false, updatable = false)
    @CreatedDate
    public Date getCreatedTime() {
        return createdTime;
    }

    @Column(name = "LAST_UPDATED_BY")
    @LastModifiedBy
    private String lastUpdatedBy;

    @Column(name = "LAST_UPDATED_TIME")
    @LastModifiedDate
    private Date lastUpdatedTime;
}