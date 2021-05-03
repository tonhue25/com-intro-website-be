package com.altek.intro.dto;

import lombok.Data;

@Data
public class MenuDTO extends AbstractDTO {
    private String label;

    private String menuTo;

    private String exact;

    private String icon;
}
