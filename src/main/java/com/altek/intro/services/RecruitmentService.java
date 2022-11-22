package com.altek.intro.services;


import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.RecruitmentRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.RecruitmentResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RecruitmentService extends AbstractService{
    List<RecruitmentResponseDto> getAllRecruitment();

    BaseResponse getList(BaseRequest requestDto);

    RecruitmentResponseDto create(RecruitmentRequestDto request);

    RecruitmentResponseDto delete(Long id);

}
