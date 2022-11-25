package com.altek.intro.services.impl;


import com.altek.intro.dto.request.LeadershipRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.LeadershipResponseDto;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.MenuResponseDto;
import com.altek.intro.entities.Leadership;
import com.altek.intro.entities.LeadershipTranslate;
import com.altek.intro.entities.MenuTranslate;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.LeadershipMapper;
import com.altek.intro.repository.LeadershipRepository;
import com.altek.intro.repository.LeadershipTranslateRepository;
import com.altek.intro.services.LeadershipService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LeadershipServiceImpl extends AbstractServiceImpl implements LeadershipService {
    @Autowired
    private LeadershipRepository leadershipRepository;

    @Autowired
    private LeadershipTranslateRepository leadershipTranslateRepository;

    @Autowired
    private LeadershipMapper leadershipMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BaseResponse getAllLeadership(String lang) {
        try {
            List<LeadershipResponseDto> leadershipDTOS = leadershipTranslateRepository.findAll(lang);
            ListResponseDto<LeadershipResponseDto> response = new ListResponseDto<>();
            response.setList(leadershipDTOS);
            response.setPage(1);
            response.setSize(leadershipDTOS.size());
            response.setTotalPages(1);
            response.setRecordPerPage(leadershipDTOS.size());
            response.setLanguage(lang);
            return new BaseResponse(Constant.SUCCESS, "get.list.leadership", leadershipDTOS);
        } catch (Exception ex) {
            return new BaseResponse(Constant.FAIL, ex.getMessage());
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public LeadershipResponseDto create(LeadershipRequestDto request) {
        Leadership entity = new Leadership();
        if(!DataUtil.isEmpty(request.getId())){
            Optional<Leadership> optional = leadershipRepository.findById(request.getId());
            if(optional.isPresent()){
                entity = optional.get();
            }
        }
        entity = (Leadership) leadershipMapper.convertToEntity(request, entity);
        entity.setStatus(Constant.INSERT);
        entity = leadershipRepository.save(entity);
        LeadershipResponseDto response = modelMapper.map(entity, LeadershipResponseDto.class);
        return response;
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public LeadershipResponseDto delete(Long id) {
        Optional<Leadership> optional = leadershipRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("leadership.not.found.with.id:%s", id));
        }
        Leadership entity = optional.get();
        entity.setStatus(Constant.DELETE);
        leadershipRepository.save(entity);
        LeadershipResponseDto response = modelMapper.map(entity, LeadershipResponseDto.class);
        return response;
    }
}
