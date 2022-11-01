package com.altek.intro.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "ALT_HISTORY")
public class HistoryEntity extends AbstractEntity{

    @Column(name = "TIMELINE")
    private Date timeLine;

    @Column(name = "EVENT")
    private String event;

}
