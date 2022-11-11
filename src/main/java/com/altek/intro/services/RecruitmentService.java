package com.altek.intro.services;


import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.RecruitmentResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RecruitmentService extends AbstractService{
    List<RecruitmentResponseDTO> getAllRecruitment();

    ListResponseDto<RecruitmentResponseDTO> getList(ListRequestDto requestDto);

}
