package com.altek.intro.service;

import com.altek.intro.dto.request.ContactRequestDto;
import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.enums.ContactType;
import org.springframework.stereotype.Service;

@Service
public interface ContactService extends AbstractService{

    BaseResponse create(ContactRequestDto request);

    BaseResponse delete(Long id);

    BaseResponse getListContact(BaseRequest<ContactType> requestDto);
}
