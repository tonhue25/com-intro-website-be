package com.altek.intro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageContentResponseDTO {

    private Long id;
    private String pageTitle;
    private String shortDescription;
    private String menuCode;
    private String address;
    private String phoneNumber;
    private String image;
    private String timeLine;
    private String eventName;
    private Long menuId;

}
