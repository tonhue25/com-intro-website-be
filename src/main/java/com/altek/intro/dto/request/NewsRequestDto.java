package com.altek.intro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsRequestDto {
    private Long id;
    private String title;
    private String thumbnail;
    private String shortDescription;
    private String detail;
    private String languageId;

}
