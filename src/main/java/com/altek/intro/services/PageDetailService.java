package com.altek.intro.services;

import com.altek.intro.dto.request.PageDetailRequestDto;
import com.altek.intro.dto.response.BaseResponse;

public interface PageDetailService extends AbstractService{

    BaseResponse getByPage(Long id);

    BaseResponse create(PageDetailRequestDto request);

    BaseResponse delete(Long id);

}
