package com.altek.intro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageTranslateResponseDto extends AbstractResponseDto {
    private String pageTitle;
    private String shortDescription;
    private String languageId;
    private Long pageId;
    private String detail;
}
