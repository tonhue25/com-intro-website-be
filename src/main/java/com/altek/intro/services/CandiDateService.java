package com.altek.intro.services;

import com.altek.intro.dto.request.CandidateRequestDto;
import com.altek.intro.dto.response.CandidateResponseDto;

import java.util.List;

public interface CandiDateService extends AbstractService {

    List<CandidateResponseDto> getAll();

    CandidateResponseDto create(CandidateRequestDto request);

    CandidateResponseDto delete(Long id);

}
