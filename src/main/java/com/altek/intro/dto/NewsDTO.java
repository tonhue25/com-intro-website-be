package com.altek.intro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTO extends AbstractDTO {
    private int status;
    private String title;
    private String thumbnail;
    private String shortDescription;

}
