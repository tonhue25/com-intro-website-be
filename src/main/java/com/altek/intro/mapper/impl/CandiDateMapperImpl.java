package com.altek.intro.mapper.impl;
import com.altek.intro.dto.response.CandidateResponseDto;
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
    public List<CandidateResponseDto> mapList(List<Candidate> list) {
        List<CandidateResponseDto> response = list.stream().map(item -> modelMapper.map(item, CandidateResponseDto.class))
                .collect(Collectors.toList());
        return response;
    }
}
