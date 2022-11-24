package com.altek.intro.mapper;

import com.altek.intro.dto.response.CandidateResponseDto;
import com.altek.intro.entities.Candidate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CandidateMapper extends AbstractMapper {
    List<CandidateResponseDto> mapList(List<Candidate> list);
}