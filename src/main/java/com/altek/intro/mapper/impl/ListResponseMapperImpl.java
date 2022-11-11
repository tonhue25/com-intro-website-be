package com.altek.intro.mapper.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.mapper.ListResponseMapper;

@Component
public class ListResponseMapperImpl<D, E> implements ListResponseMapper<D,E>{

    public ListResponseDto<D> setDataListResponse(List<D> listDTO,
            Page<E> pageEntity, Pageable pageable) {
        ListResponseDto<D> responseDto = new ListResponseDto<>();
        responseDto.setData(listDTO);
        responseDto.setSize(pageEntity.getNumberOfElements());
        responseDto.setRecordPerPage(pageable.getPageSize());
        responseDto.setTotalPages(pageEntity.getTotalPages());
        int pageNumber = pageable.getPageNumber();
        if (pageEntity.getTotalPages() > 0) {
            pageNumber = pageNumber + 1;
        }
        responseDto.setPage(pageNumber);
        return responseDto;
    }

    
}
