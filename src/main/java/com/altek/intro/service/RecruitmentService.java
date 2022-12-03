package com.altek.intro.service;


import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.RecruitmentRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.RecruitmentResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface RecruitmentService extends AbstractService{
    BaseResponse getList(BaseRequest requestDto);
}
