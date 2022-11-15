package com.altek.intro.mapper;

import com.altek.intro.dto.response.CandidateResponseDTO;
import com.altek.intro.entities.Candidate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CandiDateMapper extends AbstractMapper {
    List<CandidateResponseDTO> mapList(List<Candidate> list);
}