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

    public static String convertDatetoString(Date date) {
        String formate = "dd-MM-yyyy";
        String dateString = "";
        try {
            if (date == null) {
                String m = "";
            } else {
                dateString = new SimpleDateFormat(formate).format(date);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
        return dateString;
    }
    public PageResponseDto(Long id, Integer status, String createdBy, Date createdTime, String lastUpdatedBy, Date lastUpdatedTime, String pageTitle, String shortDescription, String image, String timeLine, Long menuId, String url) {
        super(id, status, createdBy, convertDatetoString(createdTime), lastUpdatedBy, convertDatetoString(lastUpdatedTime));
        this.pageTitle = pageTitle;
        this.shortDescription = shortDescription;
        this.image = image;
        this.timeLine = timeLine;
        this.menuId = menuId;
        this.url = url;
    }
}
