package com.altek.intro.mapper;

import com.altek.intro.dto.response.ContactResponseDTO;
import com.altek.intro.dto.response.RecruitmentResponseDTO;
import com.altek.intro.entities.ContactEntity;
import com.altek.intro.entities.RecruitmentEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ContactMapper extends  AbstractMapper {

    List<ContactResponseDTO> mapList(List<ContactEntity> list);

}
