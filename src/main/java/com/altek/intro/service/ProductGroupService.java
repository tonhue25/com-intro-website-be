package com.altek.intro.service;

import com.altek.intro.dto.request.ProductGroupRequestDto;
import com.altek.intro.dto.response.BaseResponse;

public interface ProductGroupService extends AbstractService{
    BaseResponse getListProductGroup();
    BaseResponse createOrUpdate(ProductGroupRequestDto dto);

    BaseResponse delete(Long id);
}
