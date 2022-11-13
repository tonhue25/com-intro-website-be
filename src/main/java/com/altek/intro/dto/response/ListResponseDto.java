package com.altek.intro.dto.response;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListResponseDto<D> {

    private Integer size;
    private Integer page;
    private Integer totalPages;
    private Integer recordPerPage;
    private List<D> list;
    
}
