package com.altek.intro.service;

import com.altek.intro.dto.request.CandidateRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.CandidateResponseDto;

import java.util.List;

public interface CandidateService extends AbstractService {
    BaseResponse getListCandidate();
    BaseResponse create(CandidateRequestDto request);
    BaseResponse delete(Long id);
}
