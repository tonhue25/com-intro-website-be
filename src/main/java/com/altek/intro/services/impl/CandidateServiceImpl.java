package com.altek.intro.services.impl;

import com.altek.intro.dto.request.CandidateRequestDto;
import com.altek.intro.dto.response.CandidateResponseDto;
import com.altek.intro.entities.Candidate;
import com.altek.intro.entities.Recruitment;
import com.altek.intro.entities.RecruitmentCandidate;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.CandidateMapper;
import com.altek.intro.repository.CandidateRepository;
import com.altek.intro.repository.RecruitmentCandidateRepository;
import com.altek.intro.repository.RecruitmentRepository;
import com.altek.intro.services.CandidateService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl extends AbstractServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candiDateRepository;

    @Autowired
    private CandidateMapper candiDateMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CandidateMapper candidateMapper;

    @Autowired
    private RecruitmentCandidateRepository recruitmentCandidateRepository;

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Override
    public List<CandidateResponseDto> getAll() {
        try {
            List<CandidateResponseDto> responseDtoList = new ArrayList<>();
            List<Candidate> cadidateEntity = candiDateRepository.findAll();
            CandidateResponseDto dto = new CandidateResponseDto();
            if (CollectionUtils.isNotEmpty(cadidateEntity)) {
                responseDtoList = cadidateEntity.stream().map(item -> (CandidateResponseDto) candiDateMapper.convertToDTO(dto, item)).collect(Collectors.toList());
            }
            return responseDtoList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public CandidateResponseDto create(CandidateRequestDto request) {
        if (DataUtil.isEmpty(request.getRecruitmentId())) {
            throw new IllegalArgumentException("recruitment.id.not.found");
        } else {
            Optional<Recruitment> optionalRecruitment = recruitmentRepository.findById(request.getRecruitmentId());
            if (!optionalRecruitment.isPresent()) {
                throw new ResourceNotFoundException(String.format("recruitment.not.found.with.id:%s", request.getRecruitmentId()));
            }
            Recruitment recruitment = optionalRecruitment.get();
            Candidate entity = new Candidate();
            if (!DataUtil.isEmpty(request.getPhoneNumber())) {
                Optional<Candidate> optional = candiDateRepository.findByPhoneNumber(request.getPhoneNumber());
                if (optional.isPresent()) {
                    entity = optional.get();
                }
            }
            Date dateOfBirth = DataUtil.stringToDate(request.getDateOfBirth());
            entity = candidateMapper.convertToEntity(request, entity, dateOfBirth);
            entity = candiDateRepository.save(entity);
            if (!DataUtil.isEmpty(entity.getId())) {
                Optional<RecruitmentCandidate> optionalRecruitmentCandidate = recruitmentCandidateRepository.findByRecruitmentAndCandidate(recruitment,entity);
                if(!optionalRecruitmentCandidate.isPresent()){
                    RecruitmentCandidate recruitmentCandidate = new RecruitmentCandidate(Constant.ACTIVE,recruitment,entity);
                    recruitmentCandidate = recruitmentCandidateRepository.save(recruitmentCandidate);
                }
            }
            CandidateResponseDto response = modelMapper.map(entity, CandidateResponseDto.class);
            return response;
        }
    }

    @Override
    public CandidateResponseDto delete(Long id) {
        return null;
    }
}
