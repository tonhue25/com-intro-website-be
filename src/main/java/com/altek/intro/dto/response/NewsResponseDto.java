package com.altek.intro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class NewsResponseDto extends AbstractResponseDto {
    private String title;
    private String languageId;
    private Long newsId;
    private String detail;
    private String shortDescription;
    private String thumbnail;

    public NewsResponseDto(Long id, Integer status, String createdBy, String createdTime, String lastUpdatedBy, String lastUpdatedTime,
                           String title, String languageId, Long newsId, String detail,
                           String shortDescription, String thumbnail) {
        super(id, status, createdBy, createdTime, lastUpdatedBy, lastUpdatedTime);
        this.title = title;
        this.languageId = languageId;
        this.newsId = newsId;
        this.detail = detail;
        this.shortDescription = shortDescription;
        this.thumbnail = thumbnail;
    }
}
