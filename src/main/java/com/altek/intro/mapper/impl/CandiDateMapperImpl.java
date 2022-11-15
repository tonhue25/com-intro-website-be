package com.altek.intro.mapper.impl;

import com.altek.intro.dto.response.CandidateResponseDTO;
import com.altek.intro.entities.Candidate;
import com.altek.intro.mapper.CandiDateMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CandiDateMapperImpl extends AbstractMapperImpl implements CandiDateMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CandidateResponseDTO> mapList(List<Candidate> list) {
        List<CandidateResponseDTO> response = list.stream().map(item -> modelMapper.map(item, CandidateResponseDTO.class))
                .collect(Collectors.toList());
        return response;
    }
}
