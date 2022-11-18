package com.altek.intro.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsDetailResponseDto extends AbstractResponseDto {

    private Long newsDetailId;
    private String content;
    private Long newsId;
}
