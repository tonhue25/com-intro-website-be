package com.altek.intro.mapper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.altek.intro.dto.response.ListResponseDto;

public interface ListResponseMapper<D, E> {

    public ListResponseDto<D> setDataListResponse(List<D> listDTO,
            Page<E> pageEntity, Pageable pageable);
     
}
