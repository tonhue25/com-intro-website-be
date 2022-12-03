package com.altek.intro.service;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.response.BaseResponse;

public interface RoleService extends AbstractService{
    BaseResponse getListRole();

    BaseResponse createOrUpdate(BaseRequest request);

    BaseResponse delete(Long id);
}
