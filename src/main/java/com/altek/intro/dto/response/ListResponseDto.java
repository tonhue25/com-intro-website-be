package com.altek.intro.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListResponseDto<T> {
    
    private Integer size;
    private Integer page;
    private Integer totalPages;
    private Integer recordPerPage;
    private List<T> data;
    
}
