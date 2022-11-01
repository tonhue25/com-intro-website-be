package com.altek.intro.dto;

import lombok.Data;

import java.util.Date;

@Data
public class HistoryDTO extends AbstractDTO{
    private Date timeLine;
    private String event;
}
