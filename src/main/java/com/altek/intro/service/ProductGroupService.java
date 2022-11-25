package com.altek.intro.service;

import com.altek.intro.dto.response.BaseResponse;

public interface ProductGroupService extends AbstractService{
    BaseResponse getAll();
}
