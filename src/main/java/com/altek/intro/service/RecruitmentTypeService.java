package com.altek.intro.service;

import com.altek.intro.dto.response.BaseResponse;

public interface RecruitmentTypeService extends AbstractService{
    BaseResponse getAll(String language);
}
