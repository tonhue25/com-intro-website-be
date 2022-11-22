package com.altek.intro.entities;


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
public abstract class AbstractEntity{

    private Long id;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "STATUS")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    private String createdBy;

    @Column(name = "CREATED_BY", nullable = true)
    @CreatedBy
    public String getCreatedBy() {
        return createdBy;
    }

    private Date createdTime;
    @Column(name = "CREATED_TIME", nullable = false, updatable = false)
    @CreatedDate
    public Date getCreatedTime() {
        return createdTime;
    }

    private String lastUpdatedBy;

    @Column(name = "LAST_UPDATED_BY")
    @LastModifiedBy
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    private Date lastUpdatedTime;
    @Column(name = "LAST_UPDATED_TIME")
    @LastModifiedDate
    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }
}
