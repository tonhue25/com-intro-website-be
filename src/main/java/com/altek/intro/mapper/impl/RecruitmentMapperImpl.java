package com.altek.intro.mapper.impl;

import com.altek.intro.dto.response.RecruitmentResponseDto;
import com.altek.intro.entity.Recruitment;
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

    public List<RecruitmentResponseDto> mapList(List<Recruitment> list) {
        List<RecruitmentResponseDto> response = list.stream().map(item -> modelMapper.map(item, RecruitmentResponseDto.class))
				.collect(Collectors.toList());
        return response;
    }
}
