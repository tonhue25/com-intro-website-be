package com.altek.intro.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.altek.intro.dto.PageContentDTO;
import com.altek.intro.dto.response.BaseResponse;

@Service
public interface PageContentService extends AbstractService{

    List<PageContentDTO> getAll();

    List<PageContentDTO> listPageContentByMenuId(Long id);

    BaseResponse create(PageContentDTO request);

    BaseResponse delete(Long id);
}
