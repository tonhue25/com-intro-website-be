package com.altek.intro.services;


import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.request.RecruitmentRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.RecruitmentResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RecruitmentService extends AbstractService{
    List<RecruitmentResponseDTO> getAllRecruitment();

    BaseResponse getList(ListRequestDto requestDto);

    RecruitmentResponseDTO create(RecruitmentRequestDTO request);

    RecruitmentResponseDTO delete(Long id);

}
