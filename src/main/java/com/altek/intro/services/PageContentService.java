package com.altek.intro.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.altek.intro.dto.request.PageContentRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.PageContentResponseDTO;


@Service
public interface PageContentService extends AbstractService {

    List<PageContentResponseDTO> getAll();

    List<PageContentResponseDTO> listPageContentByMenuId(Long id);

    BaseResponse create(PageContentRequestDTO request);

    BaseResponse delete(Long id);

}
