package com.altek.intro.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.RecruitmentResponseDTO;

@Service
public interface RecruitmentService extends AbstractService{
    
    List<RecruitmentResponseDTO> getAllRecruitment();

    ListResponseDto<RecruitmentResponseDTO> getList(ListRequestDto requestDto);

}
