package com.altek.intro.service;

import com.altek.intro.dto.request.ProductGroupRequestDto;
import com.altek.intro.dto.response.BaseResponse;

public interface ProductGroupService extends AbstractService{
    BaseResponse getProductGroups();
    BaseResponse createOrUpdateProductGroup(ProductGroupRequestDto dto);
    BaseResponse deleteProductGroup(Long id);
}
