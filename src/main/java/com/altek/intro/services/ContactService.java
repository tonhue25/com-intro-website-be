package com.altek.intro.services;

import com.altek.intro.dto.request.ContactRequestDto;
import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.response.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public interface ContactService extends AbstractService{

    BaseResponse getAllContact();

    BaseResponse create(ContactRequestDto request);

    BaseResponse delete(Long id);

    BaseResponse getAllContact(BaseRequest dto);
}
