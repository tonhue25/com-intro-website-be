package com.altek.intro.dto.request;

import lombok.Data;

@Data
public class MenuRequestDTO {
    private Long id;
    private String label;
    private String link;
    private String status;
}
