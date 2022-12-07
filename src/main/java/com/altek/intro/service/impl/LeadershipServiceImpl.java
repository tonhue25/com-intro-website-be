package com.altek.intro.service.impl;


import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ContactResponseDto;
import com.altek.intro.dto.response.LeadershipResponseDto;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.entity.Contact;
import com.altek.intro.enums.EmployeeType;
import com.altek.intro.exception.ResourceNotFoundException;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class LeadershipServiceImpl extends AbstractServiceImpl implements LeadershipService {

    @Autowired
    private LeadershipTranslateRepository leadershipTranslateRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BaseResponse getLeaderships(BaseRequest request) {
        List<String> enumTypes = request.getEnumTypes();
        List<EmployeeType> employeeTypes = new ArrayList<>();
        if (DataUtil.isEmpty(enumTypes)) {
            employeeTypes = EmployeeType.getAllEmployeeType();
        } else {
            employeeTypes = enumTypes.stream().map(item -> modelMapper.map(item, EmployeeType.class)).collect(Collectors.toList());
            if (!EmployeeType.getAllEmployeeType().containsAll(employeeTypes)) {
                throw new ResourceNotFoundException(String.format("employee.type.invalid:%s", Arrays.asList(request.getEnumTypes())));
            }
        }
        Pageable pageable = getPageable(request);
        Page<LeadershipResponseDto> page = leadershipTranslateRepository.getListLeadership(request.getLanguage(), employeeTypes, pageable);
        if (!CollectionUtils.isNotEmpty(page.getContent())) {
            return new BaseResponse(Constant.SUCCESS, "get.list.leadership", Constant.EMPTY_LIST);
        }
        ListResponseDto<LeadershipResponseDto> response = new ListResponseDto<>(pageable, page, page.getContent(), request.getLanguage());
        return new BaseResponse(Constant.SUCCESS, "get.list.leadership", response);
    }
}
