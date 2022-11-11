package com.altek.intro.entites;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

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
public class AbstractEntity {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
	private Long id;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "CREATED_BY", nullable = true)
    @CreatedBy
    private String createdBy;

    @Column(name = "CREATED_TIME", nullable = false, updatable = false, columnDefinition = "datetime")
    @CreatedDate
    private Date createdTime;

    @Column(name = "LAST_UPDATED_BY")
    @LastModifiedBy
    private String lastUpdatedBy;

    @Column(name = "LAST_UPDATED_TIME" , columnDefinition = "datetime")
    @LastModifiedDate
    private Date lastUpdatedTime;
}