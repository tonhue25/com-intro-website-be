package com.altek.intro.model;

import lombok.Data;

import java.util.Date;

@Data
public class AbstractModel {
    private Long id;
    private int status;
    private String createdBy;
    private Date createdTime;
    private String lastUpdatedBy;
    private Date lastUpdatedTime;
}
