package com.altek.intro.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AbstractDTO {
    private Long id;
    private int status;
    private String createdBy;
    private Date createdTime;
    private String lastUpdatedBy;
    private Date lastUpdatedTime;
    private String description;
}
