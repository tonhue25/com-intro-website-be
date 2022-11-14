package com.altek.intro.mapper.impl;

import com.altek.intro.dto.response.RecruitmentResponseDTO;
import com.altek.intro.entities.Recruitment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.altek.intro.mapper.RecruitmentMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecruitmentMapperImpl extends AbstractMapperImpl implements RecruitmentMapper {

    @Autowired
    private ModelMapper modelMapper;

    public List<RecruitmentResponseDTO> mapList(List<Recruitment> list) {
        List<RecruitmentResponseDTO> response = list.stream().map(item -> modelMapper.map(item, RecruitmentResponseDTO.class))
				.collect(Collectors.toList());
        return response;
    }
}
