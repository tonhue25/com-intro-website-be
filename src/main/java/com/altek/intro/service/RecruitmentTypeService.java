package com.altek.intro.service;

import com.altek.intro.dto.request.RecruitmentTypeTranslateRequestDto;
import com.altek.intro.dto.response.BaseResponse;

public interface RecruitmentTypeService extends AbstractService{
    BaseResponse getRecruitmentTypes(String language);
    BaseResponse createOrUpdateRecruitmentType(RecruitmentTypeTranslateRequestDto request);
    BaseResponse deleteRecruitmentType(Long id);
}
