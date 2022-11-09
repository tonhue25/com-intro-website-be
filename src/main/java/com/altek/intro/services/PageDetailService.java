package com.altek.intro.services;

import com.altek.intro.dto.PageDetailDTO;
import com.altek.intro.dto.response.BaseResponse;

public interface PageDetailService extends AbstractService{
    PageDetailDTO getByPageContentId(Long id);

    BaseResponse create(PageDetailDTO request);

    BaseResponse delete(Long id);

}
