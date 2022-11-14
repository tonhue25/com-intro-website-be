package com.altek.intro.services;

import java.util.List;

import com.altek.intro.dto.request.ListRequestDto;
import org.springframework.stereotype.Service;

import com.altek.intro.dto.request.PageContentRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.PageContentResponseDTO;


@Service
public interface PageContentService extends AbstractService {

    BaseResponse getAll();

    BaseResponse listPageContentByMenuId(Long id);

    BaseResponse create(PageContentRequestDTO request);

    BaseResponse delete(Long id);

    BaseResponse listPageContent(ListRequestDto requestDto);
}
