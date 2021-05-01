package com.altek.intro.dto;

import java.util.Date;

public class AbstractDTO {

    private Long id;
    private int activeFlag;
    private Date createDate;
    private Date updateDate;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getActiveFlag() {
        return activeFlag;
    }
    public void setActiveFlag(int activeFlag) {
        this.activeFlag = activeFlag;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}

