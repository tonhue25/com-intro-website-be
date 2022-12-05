package com.altek.intro.service.impl;

import com.altek.intro.dto.request.ContactRequestDto;
import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ContactResponseDto;

import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.entity.Contact;
import com.altek.intro.enums.ContactType;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.mapper.ContactMapper;
import com.altek.intro.mapper.ListResponseMapper;
import com.altek.intro.repository.ContactRepository;
import com.altek.intro.service.ContactService;
import com.altek.intro.util.Constant;

import com.altek.intro.util.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.ArrayList;
import java.util.Arrays;
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
    public BaseResponse create(ContactRequestDto request) {
        try {
            Contact entity = new Contact();
            ContactType type = modelMapper.map(request.getType(), ContactType.class);
            if (!(type == ContactType.FEEDBACK)) {
                if (!DataUtil.isEmpty(request.getPhoneNumber()) || !DataUtil.isEmpty(request.getEmail())) {
                    Optional<Contact> optional = contactRepository.checkExist(request.getEmail(), request.getPhoneNumber(), type);
                    if (optional.isPresent()) {
                        entity = optional.get();
                    }
                }
            }
            entity = contactMapper.convertToEntity(entity, request);
            entity = contactRepository.save(entity);
            ContactResponseDto response = modelMapper.map(entity, ContactResponseDto.class);
            return new BaseResponse(Constant.SUCCESS, "create.or.update.contact", response);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "create.or.update.contact", e.getMessage());
        }
    }

    @Override
    public BaseResponse delete(Long id) {
        try {
            Optional<Contact> optional = contactRepository.findById(id);
            if (!optional.isPresent()) {
                throw new ResourceNotFoundException(String.format("contact.not.found.with.id:%s", id));
            }
            Contact entity = optional.get();
            entity.setStatus(Constant.DELETE);
            entity = contactRepository.save(entity);
            if (entity.getStatus() == Constant.DELETE) {
                return new BaseResponse(Constant.SUCCESS, "delete.contact");
            }
            return new BaseResponse(Constant.FAIL, "delete.contact");
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "delete.product.group", e.getMessage());
        }
    }

    @Override
    public BaseResponse getListContact(BaseRequest requestDto) {
        List<ContactType> enums = new ArrayList<>();
        if (DataUtil.isEmpty(requestDto.getEnumTypes())) {
            enums = ContactType.getAllContactType();
        } else {
            enums = requestDto.getEnumTypes().stream().map(item -> modelMapper.map(item, ContactType.class)).collect(Collectors.toList());
            if (!ContactType.getAllContactType().containsAll(enums)) {
                throw new ResourceNotFoundException(String.format("contact.type.invalid:%s", Arrays.asList(requestDto.getEnumTypes())));
            }
        }
        Pageable pageable = getPageable(requestDto);
        Page<Contact> pageEntity = contactRepository.getList(requestDto.getSearch(), enums, pageable);
        List<Contact> listEntity = pageEntity.getContent();
        if (!CollectionUtils.isNotEmpty(listEntity)) {
            return new BaseResponse(Constant.SUCCESS, "get.list.contact", Constant.EMPTY_LIST);
        }
        List<ContactResponseDto> listResponse = contactMapper.mapList(listEntity);
        ListResponseDto<ContactResponseDto> response = new ListResponseDto<>(pageable, pageEntity, listResponse, requestDto.getLanguage());
        return new BaseResponse(Constant.SUCCESS, "get.list.contact", response);
    }
}
