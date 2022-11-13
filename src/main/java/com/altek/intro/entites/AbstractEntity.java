package com.altek.intro.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Data
public class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="STATUS")
    private int status;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Column(name="CREATED_TIME")
    private Date createdTime;

    @Column(name="LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @Column(name="LAST_UPDATED_TIME")
    private Date lastUpdatedTime;

}