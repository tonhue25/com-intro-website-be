package com.altek.intro.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.altek.intro.dto.RecruitmentDTO;
import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.response.ListResponseDto;

@Service
public interface RecruitmentService extends AbstractService{
    
    List<RecruitmentDTO> getAllRecruitment();

    ListResponseDto<RecruitmentDTO> getList(ListRequestDto requestDto);

}
