package com.altek.intro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageDetailRequestDto {

    private Long id;
    private String title;
    private String shortDescription;
    private String content;
    private String thumbnail;
    private String image;
    private Long pageId;
    private Integer status;

}
