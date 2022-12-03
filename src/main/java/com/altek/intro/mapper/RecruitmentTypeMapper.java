package com.altek.intro.mapper;

import com.altek.intro.dto.request.RecruitmentTypeTranslateRequestDto;
import com.altek.intro.entity.RecruitmentType;
import com.altek.intro.entity.RecruitmentTypeTranslate;

public interface RecruitmentTypeMapper extends AbstractMapper {
    RecruitmentTypeTranslate convertDtoToEntity(RecruitmentTypeTranslate entity, RecruitmentTypeTranslateRequestDto dto, RecruitmentType recruitmentType);

}
