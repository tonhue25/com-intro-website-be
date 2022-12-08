package com.altek.intro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageTranslateRequestDto {

    private Long id;
    private Integer status;
    //  page
    private String image;
    private String timeline;
    private String url;
    private String icon;
    private Long menuId;
    //    page translate
    private String pageTitle;
    private String shortDescription;
    private String languageId;
    private String detail;
    private Long pageId;
}
