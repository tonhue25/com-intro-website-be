package com.altek.intro.dto;

import lombok.Data;

@Data
public class MenuViewDTO {
    private Long id;
    private String label;
    private String menuTo;
    private Long exact;

}
