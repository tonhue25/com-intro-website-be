package com.altek.intro.service;

import com.altek.intro.dto.request.RecruitmentTypeTranslateRequestDto;
import com.altek.intro.dto.response.BaseResponse;

public interface RecruitmentTypeService extends AbstractService{
    BaseResponse getList(String language);
    BaseResponse createOrUpdate(RecruitmentTypeTranslateRequestDto request);
    BaseResponse delete(Long id);
}
