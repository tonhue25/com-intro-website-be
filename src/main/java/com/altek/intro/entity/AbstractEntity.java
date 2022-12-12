package com.altek.intro.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Access(AccessType.PROPERTY)
public abstract class AbstractEntity {
    private Long id;
    @Column(name = "STATUS")
    private Integer status;
    private String createdBy;
    private Date createdTime;
    private String lastUpdatedBy;
    private Date lastUpdatedTime;

    public AbstractEntity(Integer status) {
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    @Column(name = "CREATED_BY")
    @CreatedBy
    public String getCreatedBy() {
        return createdBy;
    }

    @Column(name = "CREATED_TIME")
    @CreatedDate
    public Date getCreatedTime() {
        return createdTime;
    }

    @Column(name = "LAST_UPDATED_BY")
    @LastModifiedBy
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Column(name = "LAST_UPDATED_TIME")
    @LastModifiedDate
    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }
}
