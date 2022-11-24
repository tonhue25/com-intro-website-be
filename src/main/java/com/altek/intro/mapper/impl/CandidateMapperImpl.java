package com.altek.intro.mapper.impl;
import com.altek.intro.dto.request.CandidateRequestDto;
import com.altek.intro.dto.response.CandidateResponseDto;
import com.altek.intro.entities.Candidate;
import com.altek.intro.mapper.CandidateMapper;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CandidateMapperImpl extends AbstractMapperImpl implements CandidateMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CandidateResponseDto> mapList(List<Candidate> list) {
        List<CandidateResponseDto> response = list.stream().map(item -> modelMapper.map(item, CandidateResponseDto.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public Candidate convertToEntity(CandidateRequestDto dto, Candidate entity, Date dateOfBirth){
        entity.setStatus(dto.getStatus());
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setCv(dto.getCv());
        entity.setGender(dto.getGender());
        entity.setAddress(dto.getAddress());
        entity.setDateOfBirth(dateOfBirth);
        return entity;
    }
}
