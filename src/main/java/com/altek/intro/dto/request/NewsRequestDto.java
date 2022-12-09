package com.altek.intro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequestDto {
    private Long id;
    private String title;
    private String thumbnail;
    private String shortDescription;
    private String detail;
    private String languageId;

}
