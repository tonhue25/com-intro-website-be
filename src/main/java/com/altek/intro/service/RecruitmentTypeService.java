package com.altek.intro.service;

import com.altek.intro.dto.request.RecruitmentTypeTranslateRequestDto;
import com.altek.intro.dto.response.BaseResponse;

public interface RecruitmentTypeService extends AbstractService{
    BaseResponse getListRecruitmentType(String language);
    BaseResponse createOrUpdateRecruitmentType(RecruitmentTypeTranslateRequestDto request);
    BaseResponse deleteRecruitmentType(Long id);
}
