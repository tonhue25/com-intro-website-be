package com.altek.intro.service;

import com.altek.intro.dto.request.ProductGroupRequestDto;
import com.altek.intro.dto.response.BaseResponse;

public interface ProductGroupService extends AbstractService{
    BaseResponse getListProductGroup();
    BaseResponse createOrUpdateProductGroup(ProductGroupRequestDto dto);
    BaseResponse deleteProductGroup(Long id);
}
