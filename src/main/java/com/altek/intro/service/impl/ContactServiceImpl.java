package com.altek.intro.service.impl;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.ContactRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ContactResponseDto;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.entity.Contact;
import com.altek.intro.enums.ContactType;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.mapper.ContactMapper;
import com.altek.intro.repository.ContactRepository;
import com.altek.intro.service.ContactService;
import com.altek.intro.util.Constant;
import com.altek.intro.util.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public BaseResponse createContact(ContactRequestDto request) {
        try {
            Contact contact = new Contact();
            ContactType type = modelMapper.map(request.getType(), ContactType.class);
            if (!(type == ContactType.FEEDBACK)) {
                if (!DataUtil.isEmpty(request.getPhoneNumber()) || !DataUtil.isEmpty(request.getEmail())) {
                    Optional<Contact> optional = contactRepository.checkExist(request.getEmail(), request.getPhoneNumber(), type);
                    if (optional.isPresent()) {
                        contact = optional.get();
                    }
                }
            }
            contact = contactMapper.convertToEntity(contact, request);
            contact = contactRepository.save(contact);
            ContactResponseDto response = modelMapper.map(contact, ContactResponseDto.class);
            return new BaseResponse(Constant.SUCCESS, "create.or.update.contact", response);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "create.or.update.contact", e.getMessage());
        }
    }

    @Override
    public BaseResponse deleteContact(Long id) {
        try {
            Optional<Contact> optional = contactRepository.findById(id);
            if (!optional.isPresent()) {
                throw new ResourceNotFoundException(String.format("contact.not.found.with.id:%s", id));
            }
            Contact contact = optional.get();
            contact.setStatus(Constant.DELETE);
            contact = contactRepository.save(contact);
            if (contact.getStatus() == Constant.DELETE) {
                return new BaseResponse(Constant.SUCCESS, "delete.contact");
            }
            return new BaseResponse(Constant.FAIL, "delete.contact");
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "delete.product.group", e.getMessage());
        }
    }

    @Override
    public BaseResponse getContacts(BaseRequest request) {
        List<ContactType> contactTypes = new ArrayList<>();
        if (DataUtil.isEmpty(request.getEnumTypes())) {
            contactTypes = ContactType.getAllContactType();
        } else {
            contactTypes = (List<ContactType>) request.getEnumTypes().stream().map(item -> modelMapper.map(item, ContactType.class)).collect(Collectors.toList());
            if (!ContactType.getAllContactType().containsAll(contactTypes)) {
                throw new ResourceNotFoundException(String.format("contact.type.invalid:%s", Arrays.asList(request.getEnumTypes())));
            }
        }
        Pageable pageable = getPageable(request);
        Page<Contact> page = contactRepository.getList(request.getSearch(), contactTypes, pageable);
        List<Contact> contacts = page.getContent();
        if (!CollectionUtils.isNotEmpty(contacts)) {
            return new BaseResponse(Constant.SUCCESS, "get.list.contact", Constant.EMPTY_LIST);
        }
        List<ContactResponseDto> contactResponseDtos = contactMapper.mapList(contacts);
        ListResponseDto<ContactResponseDto> response = new ListResponseDto<>(pageable, page, contactResponseDtos, request.getLanguage());
        return new BaseResponse(Constant.SUCCESS, "get.list.contact", response);
    }
}
