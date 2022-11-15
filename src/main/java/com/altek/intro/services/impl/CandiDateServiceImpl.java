package com.altek.intro.services.impl;

import com.altek.intro.dto.request.CandidateRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.CandidateResponseDTO;
import com.altek.intro.entities.Candidate;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.CandiDateMapper;
import com.altek.intro.repository.CandiDateRepository;
import com.altek.intro.services.CandiDateService;
import com.altek.intro.utils.Constant;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandiDateServiceImpl extends AbstractServiceImpl implements CandiDateService {

    @Autowired
    private CandiDateRepository candiDateRepository;

    @Autowired
    private CandiDateMapper candiDateMapper;

    @Override
    public List<CandidateResponseDTO> getAll() {
        try {
            List<CandidateResponseDTO> responseDtoList = new ArrayList<>();
            List<Candidate> cadidateEntity = candiDateRepository.findAll();
            CandidateResponseDTO dto = new CandidateResponseDTO();
            if(CollectionUtils.isNotEmpty(cadidateEntity)) {
                responseDtoList = cadidateEntity.stream().map(item -> (CandidateResponseDTO) candiDateMapper.convertToDTO(dto, item)).collect(Collectors.toList());
            }
            return responseDtoList;
        } catch (Exception e){
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public CandidateResponseDTO create(CandidateRequestDto request) {
        return null;
    }

    @Override
    public CandidateResponseDTO delete(Long id) {
        return null;
    }
}
