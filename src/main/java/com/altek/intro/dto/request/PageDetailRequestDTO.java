package com.altek.intro.dto.request;

import lombok.*;
import org.springframework.stereotype.Component;


@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class PageDetailRequestDTO {

    private Long id;
    private String title;
    private String shortDescription;
    private  String content;
    private  String thumbnail;
    private  String image;
    private Long pageContentId;
    private Integer status;
}
