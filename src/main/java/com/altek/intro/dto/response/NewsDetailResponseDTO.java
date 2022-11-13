package com.altek.intro.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsDetailResponseDTO extends AbstractResponseDTO{

    private String content;
    private Long newsId;
}
