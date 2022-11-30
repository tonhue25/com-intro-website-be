package com.altek.intro.service.impl;


import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.LeadershipRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ContactResponseDto;
import com.altek.intro.dto.response.LeadershipResponseDto;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.entity.Contact;
import com.altek.intro.entity.Leadership;
import com.altek.intro.entity.LeadershipTranslate;
import com.altek.intro.enums.ContactType;
import com.altek.intro.enums.EmployeeType;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.mapper.LeadershipMapper;
import com.altek.intro.repository.LeadershipRepository;
import com.altek.intro.repository.LeadershipTranslateRepository;
import com.altek.intro.service.LeadershipService;
import com.altek.intro.util.Constant;
import com.altek.intro.util.DataUtil;
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
    public BaseResponse getListLeadership(BaseRequest requestDto) {
        List<LeadershipResponseDto> listResponse = new ArrayList<>();
        List<String> listEnumTypes = requestDto.getEnumTypes();
        List<EmployeeType> enums = new ArrayList<>();
        ListResponseDto<LeadershipResponseDto> response = new ListResponseDto<>();
        int pageNumber = 0;
        int size = 0;
        int recordPerPage = 0;
        int totalPages = 0;
        if (DataUtil.isEmpty(listEnumTypes)) {
            enums = EmployeeType.getAllEmployeeType();
        } else {
            enums = listEnumTypes.stream().map(item -> modelMapper.map(item, EmployeeType.class)).collect(Collectors.toList());
            if(!EmployeeType.getAllEmployeeType().containsAll(enums)){
                throw new ResourceNotFoundException(String.format("employee.type.invalid:%s", Arrays.asList(requestDto.getEnumTypes())));
            }
        }
        if (DataUtil.isEmpty(requestDto.getPage()) || DataUtil.isEmpty(requestDto.getSize())) {
            listResponse = leadershipTranslateRepository.getListLeadership(requestDto.getLanguage(),enums);
            if (CollectionUtils.isNotEmpty(listResponse)) {
                size = recordPerPage = listResponse.size();
                pageNumber = totalPages = 1;
            }
        } else {
            Pageable pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize());
            if (!DataUtil.isEmpty(requestDto.getSortBy()) && !DataUtil.isEmpty(requestDto.getSortType())) {
                Sort.Direction sort = Sort.Direction.ASC;
                if (requestDto.getSortType().equals("DESC")) {
                    sort = Sort.Direction.DESC;
                }
                pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize(), Sort.by(sort, requestDto.getSortBy()));
            }
            Page<LeadershipResponseDto> pageEntity = leadershipTranslateRepository.getListLeadership(requestDto.getLanguage(),enums, pageable);
            listResponse = pageEntity.getContent();
            size = pageEntity.getNumberOfElements();
            recordPerPage = pageable.getPageSize();
            totalPages = pageEntity.getTotalPages();
            pageNumber = pageable.getPageNumber();
            if (pageEntity.getTotalPages() > 0) {
                pageNumber = pageNumber + 1;
            }
        }
        response.setLanguage(requestDto.getLanguage());
        response.setList(listResponse);
        response.setPage(pageNumber);
        response.setSize(size);
        response.setTotalPages(totalPages);
        response.setRecordPerPage(recordPerPage);
        return new BaseResponse(Constant.SUCCESS, "get.list.leadership", response);
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
