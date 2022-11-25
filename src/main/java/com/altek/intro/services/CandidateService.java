package com.altek.intro.services;

import com.altek.intro.dto.request.CandidateRequestDto;
import com.altek.intro.dto.response.CandidateResponseDto;

import java.util.List;

public interface CandidateService extends AbstractService {

    List<CandidateResponseDto> getAll();

    CandidateResponseDto saveCandidateInformation(CandidateRequestDto request);

    CandidateResponseDto delete(Long id);

}
