package com.altek.intro.dto.response;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PageResponseDto extends AbstractResponseDto {
    private String pageTitle;
    private String shortDescription;
    private String image;
    private Long menuId;
    private String timeLine;
    private String url;

    public PageResponseDto(Long id, Integer status, String createdBy, String createdTime, String lastUpdatedBy,
                           String lastUpdatedTime, String pageTitle, String shortDescription, String image,
                           Long menuId, String timeLine, String url) {
        super(id, status, createdBy, createdTime, lastUpdatedBy, lastUpdatedTime);
        this.pageTitle = pageTitle;
        this.shortDescription = shortDescription;
        this.image = image;
        this.menuId = menuId;
        this.timeLine = timeLine;
        this.url = url;
    }
}
