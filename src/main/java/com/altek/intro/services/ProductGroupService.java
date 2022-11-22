package com.altek.intro.services;

import com.altek.intro.dto.response.BaseResponse;

public interface ProductGroupService extends AbstractService{
    BaseResponse getAll();
}
