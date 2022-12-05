package com.altek.intro.service.impl;

import com.altek.intro.dto.request.CandidateRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.CandidateResponseDto;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.entity.Candidate;
import com.altek.intro.entity.Recruitment;
import com.altek.intro.entity.RecruitmentCandidate;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.mapper.CandidateMapper;
import com.altek.intro.repository.CandidateRepository;
import com.altek.intro.repository.RecruitmentCandidateRepository;
import com.altek.intro.repository.RecruitmentRepository;
import com.altek.intro.service.CandidateService;
import com.altek.intro.util.Constant;
import com.altek.intro.util.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl extends AbstractServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CandidateMapper candidateMapper;

    @Autowired
    private RecruitmentCandidateRepository recruitmentCandidateRepository;

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Override
    public BaseResponse getListCandidate() {
        try {
            List<Candidate> listEntity = candidateRepository.findAll();
            if (!CollectionUtils.isNotEmpty(listEntity)) {
                return new BaseResponse(Constant.SUCCESS, "get.list.candidate", Constant.EMPTY_LIST);
            }
            List<CandidateResponseDto> listDto = listEntity.stream().map(item -> modelMapper.map(item, CandidateResponseDto.class)).collect(Collectors.toList());
            ListResponseDto<CandidateResponseDto> response = new ListResponseDto<>(listDto);
            return new BaseResponse(Constant.SUCCESS, "get.list.candidate", response);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "get.list.candidate", e.getMessage());
        }
    }
    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public BaseResponse create(CandidateRequestDto request) {
        try {
            if (DataUtil.isEmpty(request.getRecruitmentId())) {
                throw new IllegalArgumentException("recruitment.id.not.found");
            }
            Optional<Recruitment> optionalRecruitment = recruitmentRepository.findById(request.getRecruitmentId());
            if (!optionalRecruitment.isPresent()) {
                throw new ResourceNotFoundException(String.format("recruitment.not.found.with.id:%s", request.getRecruitmentId()));
            }
            Recruitment recruitment = optionalRecruitment.get();
            Candidate entity = new Candidate();
            if (!DataUtil.isEmpty(request.getPhoneNumber())) {
                Optional<Candidate> optional = candidateRepository.findByPhoneNumber(request.getPhoneNumber());
                if (optional.isPresent()) {
                    entity = optional.get();
                }
            }
            Date dateOfBirth = DataUtil.stringToDate(request.getDateOfBirth());
            entity = candidateMapper.convertToEntity(request, entity, dateOfBirth);
            entity = candidateRepository.save(entity);
            if (!DataUtil.isEmpty(entity.getId())) {
                Optional<RecruitmentCandidate> optionalRecruitmentCandidate = recruitmentCandidateRepository.findByRecruitmentAndCandidate(recruitment, entity);
                if (!optionalRecruitmentCandidate.isPresent()) {
                    RecruitmentCandidate recruitmentCandidate = new RecruitmentCandidate(Constant.ACTIVE, recruitment, entity);
                    recruitmentCandidateRepository.save(recruitmentCandidate);
                }
            }
            CandidateResponseDto response = modelMapper.map(entity, CandidateResponseDto.class);
            return new BaseResponse(Constant.SUCCESS, "create.candidate", response);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "create.candidate", e.getMessage());
        }
    }
    @Override
    public BaseResponse delete(Long id) {
        try {
            Optional<Candidate> optional = candidateRepository.findById(id);
            if (!optional.isPresent()) {
                throw new ResourceNotFoundException(String.format("candidate.not.found.with.id:%s", id));
            }
            Candidate candidate = optional.get();
            candidate.setStatus(Constant.DELETE);
            candidate = candidateRepository.save(candidate);
            if (candidate.getStatus() == Constant.DELETE) {
                return new BaseResponse(Constant.SUCCESS, "delete.candidate",Constant.SUCCESS);
            }
            return new BaseResponse(Constant.FAIL, "delete.candidate",Constant.FAIL);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "delete.recruitment.type", e.getMessage());
        }
    }
}
