package com.altek.intro.dto;

import lombok.Data;

@Data
public class NewsDTO{

    private Long id;
    private int status;
    private String title;
    private String thumbnail;
    private String shortDescription;

}
