package com.altek.intro.service;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.response.BaseResponse;

public interface RoleService extends AbstractService{
    BaseResponse getListRole();

    BaseResponse createOrUpdateRole(BaseRequest request);

    BaseResponse deleteRole(Long id);
}
