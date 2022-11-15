package com.altek.intro.mapper;

import com.altek.intro.dto.response.RecruitmentResponseDto;
import com.altek.intro.entities.Recruitment;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface RecruitmentMapper extends  AbstractMapper {
     List<RecruitmentResponseDto> mapList(List<Recruitment> list);
}
