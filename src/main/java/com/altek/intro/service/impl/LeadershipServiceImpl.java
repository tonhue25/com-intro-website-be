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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Slf4j
public class LeadershipServiceImpl extends AbstractServiceImpl implements LeadershipService {

    @Autowired
    private LeadershipTranslateRepository leadershipTranslateRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LeadershipRepository leadershipRepository;

    @Autowired
    private LeadershipMapper leadershipMapper;

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

    private Long addNewTranslationForLeadershipAndReturnId(LeadershipRequestDto request, Long id) {
        LeadershipTranslate leadershipTranslateEntity = new LeadershipTranslate();
        leadershipTranslateEntity = insertRequestToLeadershipTranslate(id, leadershipTranslateEntity, request);
        leadershipTranslateEntity = leadershipTranslateRepository.save(leadershipTranslateEntity);
        return leadershipTranslateEntity.getLeadershipId();
    }

    private Long newLeadershipAndReturnId(LeadershipRequestDto request) {
        Leadership leadershipEntity = new Leadership();
        leadershipEntity = insertRequestToLeadership(leadershipEntity, request);
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
            if (Objects.nonNull(dtoCheckExist)) {
                reponse.setMessage("common.error.code.already");
                reponse.setHttp_code(HttpStatus.BAD_REQUEST.toString());
                return reponse;
            }

            long idResponse;
            Leadership dtoCheckAddTranslationOrNew = leadershipRepository.findLeadershipById(request.getLeadershipId());
            if (Objects.nonNull(dtoCheckAddTranslationOrNew)) {
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
            if (Objects.isNull(dtoCheckExist)) {
                reponse.setMessage("common.error.code.not.found");
                reponse.setHttp_code(HttpStatus.NOT_FOUND.toString());
                return reponse;
            } else {
                leadershipEntity = insertRequestToLeadership(leadershipEntity, request);
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
            if (!Objects.nonNull(leadershipEntity)) {
                reponse.setMessage("common.error.code.not.found");
                reponse.setHttp_code(HttpStatus.NOT_FOUND.toString());
                return reponse;
            }

            leadershipEntity.setStatus(Constant.DELETE);
            leadershipEntity = leadershipRepository.save(leadershipEntity);

            List<LeadershipTranslate> findByLeadershipId = leadershipTranslateRepository.
                    findLeadershipTranslateByLeadershipId(leadershipEntity.getId());
            for (LeadershipTranslate item : findByLeadershipId) {
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

    private Leadership insertRequestToLeadership(Leadership leadershipEntity, LeadershipRequestDto request) {
        leadershipEntity = (Leadership) leadershipMapper.convertToEntity(request, leadershipEntity);
        leadershipEntity.setStatus(Constant.INSERT);
        leadershipEntity.setTeam(modelMapper.map(request.getTeam(), EmployeeType.class));
        return leadershipEntity;
    }

    private LeadershipTranslate insertRequestToLeadershipTranslate(Long leadershipId, LeadershipTranslate leadershipTranslateEntity,
                                                                   LeadershipRequestDto request) {
        leadershipTranslateEntity.setPosition(request.getPosition());
        leadershipTranslateEntity.setInformation(request.getInformation());
        leadershipTranslateEntity.setFullName(request.getFullName());
        leadershipTranslateEntity.setLanguageId(request.getLanguageId());
        leadershipTranslateEntity.setStatus(Constant.INSERT);
        if (request.getLeadershipId() == null)
            leadershipTranslateEntity.setLeadershipId(leadershipId);
        else
            leadershipTranslateEntity.setLeadershipId(request.getLeadershipId());
        return leadershipTranslateEntity;
    }
}

