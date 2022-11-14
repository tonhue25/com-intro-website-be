package com.altek.intro.services.impl;

import com.altek.intro.dto.request.ContactRequestDTO;
import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ContactResponseDTO;

import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.RecruitmentResponseDTO;
import com.altek.intro.entities.ContactEntity;
import com.altek.intro.entities.RecruitmentEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.ContactMapper;
import com.altek.intro.mapper.ListResponseMapper;
import com.altek.intro.repository.ContactRepository;
import com.altek.intro.services.ContactService;
import com.altek.intro.utils.Constant;

import com.altek.intro.utils.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl extends AbstractServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BaseResponse getAllContact() {
        try {
            List<ContactResponseDTO> responseDTOList = new ArrayList<>();
            List<ContactEntity> contactEntities = contactRepository.findAll();
            ContactResponseDTO dto = new ContactResponseDTO();
            if (CollectionUtils.isNotEmpty(contactEntities)) {
                responseDTOList = contactEntities.stream().map(item -> (ContactResponseDTO) contactMapper.convertToDTO(dto, item)).collect(Collectors.toList());
            }
            return new BaseResponse(Constant.SUCCESS, "get.list.contact", responseDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public BaseResponse create(ContactRequestDTO request) {
        ContactEntity entity = new ContactEntity();
        if (!DataUtil.isEmpty(request.getId())) {
            Optional<ContactEntity> optional = contactRepository.findById(request.getId());
            if (optional.isPresent()) {
                entity = optional.get();
            }
        }
        entity = (ContactEntity) contactMapper.convertToEntity(request, entity);
        entity = contactRepository.save(entity);
        ContactResponseDTO response = modelMapper.map(entity, ContactResponseDTO.class);
        return new BaseResponse(Constant.SUCCESS, "add.or.update.contact.success", response);
    }

    @Override
    public BaseResponse delete(Long id) {
        Optional<ContactEntity> optional = contactRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("contact.not.found.with.id:%s", id));
        }
        ContactEntity entity = optional.get();
        entity.setStatus(Constant.DELETE);
        entity = contactRepository.save(entity);
        return new BaseResponse(Constant.SUCCESS, String.format("delete.contact.with.id:%s.success", id));
    }

    @Autowired
    ListResponseMapper<ContactResponseDTO, ContactEntity> listResponseMapper;

    @Override
    public BaseResponse getAllContact(ListRequestDto requestDto) {
        if (DataUtil.isEmpty(requestDto.getPage())) {
            throw new IllegalArgumentException("page.is.invalid");
        }
        if (DataUtil.isEmpty(requestDto.getSize())) {
            throw new IllegalArgumentException("size.is.invalid");
        }
        Pageable pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize());
        if (!DataUtil.isEmpty(requestDto.getSortBy()) && !DataUtil.isEmpty(requestDto.getSortType())) {
            Sort.Direction sort = Sort.Direction.ASC;
            if (requestDto.getSortType().equals("DESC")) {
                sort = Sort.Direction.DESC;
            }
            pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize(), Sort.by(sort, requestDto.getSortBy()));
        }
        Page<ContactEntity> pageEntity = contactRepository.getList(requestDto.getSearch(), pageable);
        List<ContactEntity> listEntity = pageEntity.getContent();
        List<ContactResponseDTO> listResponse = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listResponse = contactMapper.mapList(listEntity);
        }
        ListResponseDto<ContactResponseDTO> response = listResponseMapper.setDataListResponse(listResponse, pageEntity, pageable);
        return new BaseResponse(Constant.SUCCESS, "get.list.contact", response);
    }
}
