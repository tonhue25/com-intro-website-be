package com.altek.intro.dto;

import lombok.Data;

@Data
public class NewsDetailDTO extends AbstractDTO{
    private String content;
    private Long newsId;
}
