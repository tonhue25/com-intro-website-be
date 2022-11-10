package com.altek.intro.dto.request;

import lombok.Data;

@Data
public class MenuRequestDto {

    private Long id;
    private String label;
    private String link;
    private String status;

}
