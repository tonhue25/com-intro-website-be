package com.altek.intro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponseDTO {

    private Long id;
    private int status;
    private String title;
    private String thumbnail;
    private String shortDescription;

}
