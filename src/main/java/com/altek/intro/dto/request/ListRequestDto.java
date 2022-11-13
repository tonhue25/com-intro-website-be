package com.altek.intro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListRequestDto {

    private Integer size;
    private Integer page;
    private String search;
    private String sortType;
    private String sortBy;
    private Long id;
    
}
