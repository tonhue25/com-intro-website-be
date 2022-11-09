package com.altek.intro.services;

import com.altek.intro.dto.request.PageDetailRequestDTO;

public interface PageDetailService extends AbstractService{
    PageDetailRequestDTO getByPageContentId(Long id);

}
