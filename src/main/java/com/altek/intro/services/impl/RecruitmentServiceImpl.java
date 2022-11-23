package com.altek.intro.services.impl;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.RecruitmentRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.RecruitmentResponseDto;
import com.altek.intro.entities.ProductGroup;
import com.altek.intro.entities.Recruitment;
import com.altek.intro.entities.RecruitmentType;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.ListResponseMapper;
import com.altek.intro.mapper.RecruitmentMapper;
import com.altek.intro.repository.ProductGroupRepository;
import com.altek.intro.repository.RecruitmentRepository;
import com.altek.intro.repository.RecruitmentTypeRepository;
import com.altek.intro.services.RecruitmentService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecruitmentServiceImpl extends AbstractServiceImpl implements RecruitmentService {
    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    private RecruitmentMapper recruitmentMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ListResponseMapper<RecruitmentResponseDto, Recruitment> listResponseMapper;

    @Autowired
    private RecruitmentTypeRepository recruitmentTypeRepository;

    @Autowired
    private ProductGroupRepository productGroupRepository;

    @Override
    public BaseResponse getList(BaseRequest request) {
        ListResponseDto<RecruitmentResponseDto> response = new ListResponseDto<>();
        List<RecruitmentResponseDto> listResponse = new ArrayList<>();
        List<String> locations = new ArrayList<>();
        int pageNumber = 1;
        int size = 0;
        int recordPerPage = 0;
        int totalPages = 1;
        List<Long> types = new ArrayList<>();
        List<Long> groups = new ArrayList<>();
        if (DataUtil.isEmpty(request.getLocations())) {
            locations = Arrays.asList(Constant.HCM, Constant.HN , Constant.HCMC);
        } else {
            locations = request.getLocations();
        }
        if (DataUtil.isEmpty(request.getTypes())) {
            types = recruitmentTypeRepository.findAll().stream()
                    .map(RecruitmentType::getId)
                    .collect(Collectors.toList());
        } else {
            types = request.getTypes();
        }
        if (DataUtil.isEmpty(request.getGroups())) {
            groups = productGroupRepository.findAll().stream()
                    .map(ProductGroup::getId)
                    .collect(Collectors.toList());
        } else {
            groups = request.getGroups();
        }
        if (DataUtil.isEmpty(request.getPage()) || DataUtil.isEmpty(request.getSize())) {
            listResponse = recruitmentRepository.getList(request.getSearch(), request.getLanguage(), locations, types, groups);
            size = recordPerPage = listResponse.size();
        } else {
            Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());
            if (!DataUtil.isEmpty(request.getSortBy()) && !DataUtil.isEmpty(request.getSortType())) {
                Sort.Direction sort = Sort.Direction.ASC;
                if (request.getSortType().equals("DESC")) {
                    sort = Sort.Direction.DESC;
                }
                pageable = PageRequest.of(request.getPage() - 1, request.getSize(),
                        Sort.by(sort, request.getSortBy()));
            }
            Page<RecruitmentResponseDto> pageEntity = recruitmentRepository.getList(request.getSearch(), request.getLanguage(), locations, types, groups, pageable);
            listResponse = pageEntity.getContent();
            size = pageEntity.getNumberOfElements();
            recordPerPage = pageable.getPageSize();
            totalPages = pageEntity.getTotalPages();
            pageNumber = pageable.getPageNumber();
            if (pageEntity.getTotalPages() > 0) {
                pageNumber = pageNumber + 1;
            }
        }
        response.setLanguage(request.getLanguage());
        response.setList(listResponse);
        response.setPage(pageNumber);
        response.setSize(size);
        response.setTotalPages(totalPages);
        response.setRecordPerPage(recordPerPage);
        return new BaseResponse(Constant.SUCCESS, "get.list.recruitment", response);
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public RecruitmentResponseDto create(RecruitmentRequestDto request) {
        Recruitment entity = new Recruitment();
        if (!DataUtil.isEmpty(request.getId())) {
            Optional<Recruitment> optional = recruitmentRepository.findById(request.getId());
            if (optional.isPresent()) {
                entity = optional.get();
            }
        }
        entity = (Recruitment) recruitmentMapper.convertToEntity(request, entity);
        entity.setStatus(Constant.INSERT);
        entity = recruitmentRepository.save(entity);
        RecruitmentResponseDto response = modelMapper.map(entity, RecruitmentResponseDto.class);
        return response;
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public RecruitmentResponseDto delete(Long id) {
        Optional<Recruitment> optional = recruitmentRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("Recruitment.not.found.with.id:%s", id));
        }
        Recruitment entity = optional.get();
        entity.setStatus(Constant.DELETE);
        recruitmentRepository.save(entity);
        RecruitmentResponseDto response = modelMapper.map(entity, RecruitmentResponseDto.class);
        return response;
    }
}
