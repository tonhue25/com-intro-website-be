package com.altek.intro.service;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.response.BaseResponse;

public interface RoleService extends AbstractService{
    BaseResponse getRoles();

    BaseResponse createOrUpdateRole(BaseRequest request);

    BaseResponse deleteRole(Long id);
}
