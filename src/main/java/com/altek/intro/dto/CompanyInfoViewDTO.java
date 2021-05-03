package com.altek.intro.dto;

import lombok.Data;

@Data
public class CompanyInfoViewDTO {
    private Long id;
    private String title;
    private String shortDescription;
    private String content;
    private String image;
    private Long phoneNumber;
}
