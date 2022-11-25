package com.altek.intro.mapper;

import com.altek.intro.dto.response.ContactResponseDto;
import com.altek.intro.entity.Contact;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ContactMapper extends  AbstractMapper {

    List<ContactResponseDto> mapList(List<Contact> list);

}
