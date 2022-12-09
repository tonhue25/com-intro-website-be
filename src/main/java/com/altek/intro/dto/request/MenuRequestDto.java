package com.altek.intro.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MenuRequestDto {
    private Long id;
    private String label;
    private String link;
    private String status;
    private String languageId;
    private Long parentId;
}
