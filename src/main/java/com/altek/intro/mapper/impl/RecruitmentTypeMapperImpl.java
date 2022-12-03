package com.altek.intro.mapper.impl;

import com.altek.intro.dto.request.RecruitmentTypeTranslateRequestDto;
import com.altek.intro.entity.RecruitmentType;
import com.altek.intro.entity.RecruitmentTypeTranslate;
import com.altek.intro.mapper.RecruitmentTypeMapper;
import org.springframework.stereotype.Component;

@Component
public class RecruitmentTypeMapperImpl extends AbstractMapperImpl implements RecruitmentTypeMapper {

    @Override
    public RecruitmentTypeTranslate convertDtoToEntity(RecruitmentTypeTranslate entity, RecruitmentTypeTranslateRequestDto dto, RecruitmentType recruitmentType) {
        entity.setRecruitmentType(recruitmentType);
        entity.setName(dto.getName());
        entity.setLanguageId(dto.getLanguageId());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
