package com.altek.intro.dto;

import lombok.Data;

@Data
public class MenuDTO{
    private Long id;
    private String label;
    private String link;
    private String status;
}
