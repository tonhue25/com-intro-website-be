package com.altek.intro.mapper;

import com.altek.intro.dto.response.RecruitmentResponseDTO;
import com.altek.intro.entities.Recruitment;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface RecruitmentMapper extends  AbstractMapper {
     List<RecruitmentResponseDTO> mapList(List<Recruitment> list);
}
