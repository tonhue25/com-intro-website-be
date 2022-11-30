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
        return new BaseResponse(Constant.SUCCESS, "add.or.update.contact.success", response);
    }


    @Override
    public BaseResponse delete(Long id) {
        Optional<Contact> optional = contactRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("contact.not.found.with.id:%s", id));
        }
        Contact entity = optional.get();
        if (entity.getStatus().equals(Constant.DELETE)) {
            return new BaseResponse(Constant.SUCCESS, String.format("contact.already.delete.with.id:%s", id));
        }
        entity.setStatus(Constant.DELETE);
        contactRepository.save(entity);
        return new BaseResponse(Constant.SUCCESS, String.format("delete.contact.with.id:%s.success", id));
    }

    @Autowired
    ListResponseMapper<ContactResponseDto, Contact> listResponseMapper;


    @Override
    public BaseResponse getListContact(BaseRequest<ContactType> requestDto) {
        List<Contact> listEntity = new ArrayList<>();
        List<ContactResponseDto> listResponse = new ArrayList<>();
        List<String> listContactTypes = requestDto.getEnumTypes();
        List<ContactType> enums = new ArrayList<>();
        ListResponseDto<ContactResponseDto> response = new ListResponseDto<>();
        int pageNumber = 0;
        int size = 0;
        int recordPerPage = 0;
        int totalPages = 0;
        if (DataUtil.isEmpty(listContactTypes)) {
            enums = ContactType.getAllContactType();
        } else {
            enums = listContactTypes.stream().map(item -> modelMapper.map(item, ContactType.class)).collect(Collectors.toList());
            if (!ContactType.getAllContactType().containsAll(enums)) {
                throw new ResourceNotFoundException(String.format("contact.type.invalid:%s", Arrays.asList(requestDto.getEnumTypes())));
            }
        }
        if (DataUtil.isEmpty(requestDto.getPage()) || DataUtil.isEmpty(requestDto.getSize())) {
            listEntity = contactRepository.getList(requestDto.getSearch(), enums);
            if (CollectionUtils.isNotEmpty(listEntity)) {
                size = recordPerPage = listEntity.size();
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
            Page<Contact> pageEntity = contactRepository.getList(requestDto.getSearch(), enums, pageable);
            listEntity = pageEntity.getContent();
            size = pageEntity.getNumberOfElements();
            recordPerPage = pageable.getPageSize();
            totalPages = pageEntity.getTotalPages();
            pageNumber = pageable.getPageNumber();
            if (pageEntity.getTotalPages() > 0) {
                pageNumber = pageNumber + 1;
            }
        }
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listResponse = contactMapper.mapList(listEntity);
        }
        response.setLanguage(requestDto.getLanguage());
        response.setList(listResponse);
        response.setPage(pageNumber);
        response.setSize(size);
        response.setTotalPages(totalPages);
        response.setRecordPerPage(recordPerPage);
        return new BaseResponse(Constant.SUCCESS, "get.list.contact", response);
    }
}
