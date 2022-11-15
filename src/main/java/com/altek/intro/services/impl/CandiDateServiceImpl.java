package com.altek.intro.services.impl;

import com.altek.intro.dto.request.CandidateRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.CandidateResponseDTO;
import com.altek.intro.dto.response.LeadershipResponseDTO;
import com.altek.intro.entities.Candidate;
import com.altek.intro.entities.Recruitment;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.CandiDateMapper;
import com.altek.intro.repository.CandiDateRepository;
import com.altek.intro.services.CandiDateService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandiDateServiceImpl extends AbstractServiceImpl implements CandiDateService {

    @Autowired
    private CandiDateRepository candiDateRepository;

    @Autowired
    private CandiDateMapper candiDateMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CandidateResponseDTO> getAll() {
        try {
            List<CandidateResponseDTO> responseDtoList = new ArrayList<>();
            List<Candidate> cadidateEntity = candiDateRepository.findAll();
            CandidateResponseDTO dto = new CandidateResponseDTO();
            if (CollectionUtils.isNotEmpty(cadidateEntity)) {
                responseDtoList = cadidateEntity.stream().map(item -> (CandidateResponseDTO) candiDateMapper.convertToDTO(dto, item)).collect(Collectors.toList());
            }
            return responseDtoList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public CandidateResponseDTO create(CandidateRequestDto request) {
        Candidate entity = new Candidate();
        if (!DataUtil.isEmpty(request.getId())) {
            Optional<Candidate> optional = candiDateRepository.findById(request.getId());
            if (optional.isPresent()) {
                entity = optional.get();
            }
        }
        entity = (Candidate) candiDateMapper.convertToEntity(request, entity);
        entity.setStatus(Constant.INSERT);
        entity = candiDateRepository.save(entity);
        CandidateResponseDTO response = modelMapper.map(entity, CandidateResponseDTO.class);
        return response;
    }

    @Override
    public CandidateResponseDTO delete(Long id) {
        return null;
    }
}
