package com.altek.intro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SliderResponseDTO {
    private String title;
    private String image;
    private String content;

}
