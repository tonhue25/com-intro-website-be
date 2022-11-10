package com.altek.intro.services;

import com.altek.intro.dto.request.PageDetailRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.PageDetailResponseDTO;

public interface PageDetailService extends AbstractService{
    
    PageDetailResponseDTO getByPageContentId(Long id);

    BaseResponse create(PageDetailRequestDTO request);

    BaseResponse delete(Long id);

}
