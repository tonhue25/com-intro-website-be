package com.altek.intro.service.impl;

import com.altek.intro.dto.request.ContactRequestDto;
import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ContactResponseDto;

import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.entity.Contact;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.mapper.ContactMapper;
import com.altek.intro.mapper.ListResponseMapper;
import com.altek.intro.repository.ContactRepository;
import com.altek.intro.service.ContactService;
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
            List<ContactResponseDto> responseDTOList = new ArrayList<>();
            List<Contact> contactEntities = contactRepository.findAll();
            ContactResponseDto dto = new ContactResponseDto();
            if (CollectionUtils.isNotEmpty(contactEntities)) {
                responseDTOList = contactEntities.stream().map(item -> (ContactResponseDto) contactMapper.convertToDTO(dto, item)).collect(Collectors.toList());
            }
            return new BaseResponse(Constant.SUCCESS, "get.list.contact", responseDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public BaseResponse create(ContactRequestDto request) {
        Contact entity = new Contact();

        if (!DataUtil.isEmpty(request.getPhoneNumber())) {
            Optional<Contact> optional = contactRepository.findByPhoneNumber(request.getPhoneNumber());
            if (optional.isPresent()) {
                entity = optional.get();
            }
        }
        entity = (Contact) contactMapper.convertToEntity(request, entity);
        entity = contactRepository.save(entity);
        ContactResponseDto response = modelMapper.map(entity, ContactResponseDto.class);
        return new BaseResponse(Constant.SUCCESS, "add.or.update.contact.success", response);
    }

    @Override
    public BaseResponse delete(Long id) {
        Optional<Contact> optional = contactRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("contact.not.found.with.id:%s", id));
        }
        Contact entity = optional.get();
        if(entity.getStatus().equals(Constant.DELETE)){
            return new BaseResponse(Constant.SUCCESS, String.format("contact.already.delete.with.id:%s", id));
        }
        entity.setStatus(Constant.DELETE);
        contactRepository.save(entity);
        return new BaseResponse(Constant.SUCCESS, String.format("delete.contact.with.id:%s.success", id));
    }

    @Autowired
    ListResponseMapper<ContactResponseDto, Contact> listResponseMapper;

    @Override
    public BaseResponse getAllContact(BaseRequest requestDto) {
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
        Page<Contact> pageEntity = contactRepository.getList(requestDto.getSearch(), pageable);
        List<Contact> listEntity = pageEntity.getContent();
        List<ContactResponseDto> listResponse = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listResponse = contactMapper.mapList(listEntity);
        }
        ListResponseDto<ContactResponseDto> response = listResponseMapper.setDataListResponse(listResponse, pageEntity, pageable);
        return new BaseResponse(Constant.SUCCESS, "get.list.contact", response);
    }
}
