package com.altek.intro.dto.request;

import lombok.Data;

@Data
public class NewsDetailRequestDto {
    private Long id;
    private String content;
    private Long newsId;
}