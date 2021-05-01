package com.altek.intro.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AbstractDTO {
    private Long id;
    private int activeFlag;
    private String createBy;
    private Date createDate;
    private String modifyBy;
    private Date modifyDate;
}
