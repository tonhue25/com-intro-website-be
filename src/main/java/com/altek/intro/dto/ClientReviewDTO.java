package com.altek.intro.dto;

import lombok.Data;

@Data
public class ClientReviewDTO extends AbstractDTO {
    private String name;
    private String position;
    private String content;
    private Long evaluate;
    private String image;
}
