package com.altek.intro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageContentResponseDTO extends AbstractResponseDTO{
    private String pageTitle;
    private String shortDescription;
    private String address;
    private String phoneNumber;
    private String image;
    private String timeLine;
    private String eventName;
    private Long menuId;

}
