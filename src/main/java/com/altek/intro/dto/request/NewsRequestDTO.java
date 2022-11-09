package com.altek.intro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequestDTO {

    private Long id;
    private int status;
    private String title;
    private String thumbnail;
    private String shortDescription;

}
