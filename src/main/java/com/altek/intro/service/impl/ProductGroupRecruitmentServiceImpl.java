package com.altek.intro.service.impl;

import com.altek.intro.dto.request.RecruitmentTypeTranslateRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.RecruitmentTypeResponseDto;
import com.altek.intro.entity.RecruitmentType;
import com.altek.intro.entity.RecruitmentTypeTranslate;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.mapper.RecruitmentTypeMapper;
import com.altek.intro.repository.RecruitmentTypeRepository;
import com.altek.intro.repository.RecruitmentTypeTranslateRepository;
import com.altek.intro.service.ProductGroupRecruitmentService;
import com.altek.intro.service.RecruitmentTypeService;
import com.altek.intro.util.Constant;
import com.altek.intro.util.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductGroupRecruitmentServiceImpl extends AbstractServiceImpl implements ProductGroupRecruitmentService {

}
