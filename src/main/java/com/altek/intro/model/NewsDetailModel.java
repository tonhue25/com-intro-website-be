package com.altek.intro.model;

import lombok.Data;

@Data
public class NewsDetailModel extends AbstractModel {
    private String content;
    private Long newsId;
}
