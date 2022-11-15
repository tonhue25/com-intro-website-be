package com.altek.intro.services;

import com.altek.intro.dto.request.CandidateRequestDto;
import com.altek.intro.dto.response.CandidateResponseDTO;
import com.altek.intro.repository.CandiDateRepository;

import java.util.List;

public interface CandiDateService extends AbstractService {

    List<CandidateResponseDTO> getAll();

    CandidateResponseDTO create(CandidateRequestDto request);

    CandidateResponseDTO delete(Long id);

}
