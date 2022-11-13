package com.altek.intro.entites;

<<<<<<< HEAD
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Data
public class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
=======

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
>>>>>>> tonhue

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
=======
	private Long id;
>>>>>>> tonhue

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