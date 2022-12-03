package com.altek.intro.service.impl;

import java.util.Optional;

import com.altek.intro.util.DataUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altek.intro.dto.request.PageDetailRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.PageDetailResponseDto;
import com.altek.intro.entity.Page;
import com.altek.intro.entity.PageDetail;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.mapper.PageDetailMapper;
import com.altek.intro.repository.PageRepository;
import com.altek.intro.repository.PageDetailRepository;
import com.altek.intro.service.PageDetailService;
import com.altek.intro.util.Constant;

@Service
public class PageDetailServiceImpl extends AbstractServiceImpl implements PageDetailService {

}
