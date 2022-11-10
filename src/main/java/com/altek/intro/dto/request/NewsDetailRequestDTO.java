package com.altek.intro.dto.request;

import lombok.Data;

@Data
public class NewsDetailRequestDTO {
    private String content;
    private Long newsId;
}
