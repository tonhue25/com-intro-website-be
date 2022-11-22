package com.altek.intro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponseDto extends AbstractResponseDto {
    private String title;
    private String languageId;
    private Long newsId;
    private String detail;
    private String shortDescription;
    private String thumbnail;
}
