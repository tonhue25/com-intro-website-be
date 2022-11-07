package com.altek.intro.dto;

import lombok.Data;

@Data
public class NewsDTO extends AbstractDTO{

    private String title;
    private String thumbnail;
    private String shortDescription;

}
