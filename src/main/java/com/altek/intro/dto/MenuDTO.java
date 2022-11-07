package com.altek.intro.dto;

import lombok.Data;

@Data
public class MenuDTO extends AbstractDTO {

    private String label;
    private String code;
    private String link;
}
