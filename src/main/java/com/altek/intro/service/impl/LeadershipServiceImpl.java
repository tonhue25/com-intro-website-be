package com.altek.intro.service.impl;


import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.LeadershipRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.LeadershipResponseDto;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.entity.Leadership;
import com.altek.intro.entity.LeadershipTranslate;
import com.altek.intro.enums.EmployeeType;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.exception.SystemErrorException;
import com.altek.intro.mapper.LeadershipMapper;
import com.altek.intro.repository.LeadershipRepository;
import com.altek.intro.repository.LeadershipTranslateRepository;
import com.altek.intro.service.LeadershipService;
import com.altek.intro.util.Constant;
import com.altek.intro.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
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

    private Long addNewTranslationForLeadershipAndReturnId(LeadershipRequestDto request, Long id){
        LeadershipTranslate leadershipTranslateEntity = new LeadershipTranslate();
        leadershipTranslateEntity = insertRequestToLeadershipTranslate(id, leadershipTranslateEntity, request);
        leadershipTranslateEntity = leadershipTranslateRepository.save(leadershipTranslateEntity);
        return leadershipTranslateEntity.getLeadershipId();
    }
    private Long newLeadershipAndReturnId(LeadershipRequestDto request){
        Leadership leadershipEntity = new Leadership();
        leadershipEntity = insertRequestToLeadership(leadershipEntity ,request);
        leadershipEntity = leadershipRepository.save(leadershipEntity);

        addNewTranslationForLeadershipAndReturnId(request, leadershipEntity.getId());
        return leadershipEntity.getId();
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public BaseResponse createNewLeadership(LeadershipRequestDto request) {
        log.info("#LeadershipServiceImpl.createNewLeadership   Start..... ");
        try {
            BaseResponse reponse = new BaseResponse();

            LeadershipResponseDto dtoCheckExist = leadershipTranslateRepository
                    .findByLeadershipIdAndLanguageId(request.getLeadershipId(), request.getLanguageId());
            if(Objects.nonNull(dtoCheckExist)){
                reponse.setMessage("common.error.code.already");
                reponse.setHttp_code(HttpStatus.BAD_REQUEST.toString());
                return reponse;
            }

            long idResponse;
            Leadership dtoCheckAddTranslationOrNew = leadershipRepository.findLeadershipById(request.getLeadershipId());
            if(Objects.nonNull(dtoCheckAddTranslationOrNew)){
                idResponse = addNewTranslationForLeadershipAndReturnId(request, dtoCheckAddTranslationOrNew.getId());
            } else {
                idResponse = newLeadershipAndReturnId(request);
            }

            LeadershipResponseDto data = leadershipTranslateRepository
                    .findByLeadershipIdAndLanguageId(idResponse, request.getLanguageId());
            reponse.setData(data);
            reponse.setMessage("common.insert.success");
            reponse.setHttp_code(HttpStatus.CREATED.toString());
            return reponse;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("#LeadershipServiceImpl.createNewLeadership " + ex.getMessage(), ex);
            throw new SystemErrorException("common.system.error");
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public BaseResponse updateLeadership(LeadershipRequestDto request) {
        log.info("#LeadershipServiceImpl.updateLeadership   Start..... ");
        try {
            BaseResponse reponse = new BaseResponse();

            LeadershipResponseDto dtoCheckExist = leadershipTranslateRepository
                    .findByLeadershipIdAndLanguageId(request.getId(), request.getLanguageId());
            Leadership leadershipEntity = new Leadership();
            if(Objects.isNull(dtoCheckExist)){
                reponse.setMessage("common.error.code.not.found");
                reponse.setHttp_code(HttpStatus.NOT_FOUND.toString());
                return reponse;
            } else {
                leadershipEntity = insertRequestToLeadership(leadershipEntity ,request);
                leadershipEntity = leadershipRepository.save(leadershipEntity);

                LeadershipTranslate leadershipTranslateEntity = leadershipTranslateRepository.findLeadershipTranslateByLeadershipIdAndLanguageId(request.getId(), request.getLanguageId());
                leadershipTranslateEntity = insertRequestToLeadershipTranslate(request.getId(), leadershipTranslateEntity, request);
                leadershipTranslateRepository.save(leadershipTranslateEntity);
            }

            LeadershipResponseDto data = leadershipTranslateRepository
                    .findByLeadershipIdAndLanguageId(leadershipEntity.getId(), request.getLanguageId());
            reponse.setData(data);
            reponse.setMessage("common.update.success");
            reponse.setHttp_code(HttpStatus.OK.toString());
            return reponse;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("#LeadershipServiceImpl.updateLeadership " + ex.getMessage(), ex);
            throw new SystemErrorException("common.system.error");
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public BaseResponse deleteLeadership(LeadershipRequestDto request) {
        log.info("#LeadershipServiceImpl.deleteLeadership   Start..... ");
        try {
            BaseResponse reponse = new BaseResponse();

            Leadership leadershipEntity = leadershipRepository.findLeadershipById(request.getId());
            if(!Objects.nonNull(leadershipEntity)){
                reponse.setMessage("common.error.code.not.found");
                reponse.setHttp_code(HttpStatus.NOT_FOUND.toString());
                return reponse;
            }

            leadershipEntity.setStatus(Constant.DELETE);
            leadershipEntity = leadershipRepository.save(leadershipEntity);

            List<LeadershipTranslate> findByLeadershipId = leadershipTranslateRepository.
                    findLeadershipTranslateByLeadershipId(leadershipEntity.getId());
            for(LeadershipTranslate item: findByLeadershipId){
                item.setStatus(Constant.DELETE);
                leadershipTranslateRepository.save(item);
            }

            LeadershipResponseDto data = new LeadershipResponseDto();
            data = (LeadershipResponseDto) leadershipMapper.convertToDTO(data, leadershipEntity);
            reponse.setData(data);
            reponse.setMessage("common.delete.success");
            reponse.setHttp_code(HttpStatus.OK.toString());
            return reponse;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("#LeadershipServiceImpl.deleteLeadership " + ex.getMessage(), ex);
            throw new SystemErrorException("common.system.error");
        }
    }

    private Leadership insertRequestToLeadership(Leadership leadershipEntity, LeadershipRequestDto request){
        leadershipEntity = (Leadership) leadershipMapper.convertToEntity(request, leadershipEntity);
        leadershipEntity.setStatus(Constant.INSERT);
        leadershipEntity.setTeam(modelMapper.map(request.getTeam(),EmployeeType.class));
        return leadershipEntity;
    }
    private LeadershipTranslate insertRequestToLeadershipTranslate(Long leadershipId, LeadershipTranslate leadershipTranslateEntity,
                                                                   LeadershipRequestDto request) {
        leadershipTranslateEntity.setPosition(request.getPosition());
        leadershipTranslateEntity.setInformation(request.getInformation());
        leadershipTranslateEntity.setFullName(request.getFullName());
        leadershipTranslateEntity.setLanguageId(request.getLanguageId());
        leadershipTranslateEntity.setStatus(Constant.INSERT);
        if(request.getLeadershipId() == null)
            leadershipTranslateEntity.setLeadershipId(leadershipId);
        else
            leadershipTranslateEntity.setLeadershipId(request.getLeadershipId());
        return leadershipTranslateEntity;
    }
}

