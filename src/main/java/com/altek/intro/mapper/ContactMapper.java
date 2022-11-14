package com.altek.intro.mapper;

import com.altek.intro.dto.response.ContactResponseDTO;
import com.altek.intro.entities.Contact;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ContactMapper extends  AbstractMapper {

    List<ContactResponseDTO> mapList(List<Contact> list);

}
