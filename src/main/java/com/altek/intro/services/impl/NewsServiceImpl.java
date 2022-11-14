package com.altek.intro.services.impl;

import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.request.NewsRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.NewsResponseDTO;
import com.altek.intro.entities.NewsEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.ListResponseMapper;
import com.altek.intro.mapper.NewsMapper;
import com.altek.intro.repository.NewsRepository;
import com.altek.intro.services.NewsService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.DataUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl extends AbstractServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ListResponseMapper<NewsResponseDTO, NewsEntity> listResponseMapper;

    @Override
    public BaseResponse getList(ListRequestDto requestDto) {
        if (DataUtil.isEmpty(requestDto.getPage())) {
            //
        }
        if (DataUtil.isEmpty(requestDto.getSize())) {
            //
        }
        Sort sort;
        if (requestDto.getSortType().equals("DESC")) {
            sort = Sort.by(Sort.Direction.DESC, requestDto.getSortBy());
        } else {
            sort = Sort.by(Sort.Direction.ASC, requestDto.getSortBy());
        }
        Pageable pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize(), sort);
        Page<NewsEntity> pageEntity = newsRepository.getList(requestDto.getSearch().toLowerCase(), pageable);

        List<NewsEntity> listEntity = pageEntity.getContent();
        List<NewsResponseDTO> listDTO = new ArrayList<>();
        NewsResponseDTO dto = new NewsResponseDTO();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listDTO = listEntity.stream().map(item -> (NewsResponseDTO) newsMapper.convertToDTO(dto, item)).collect(Collectors.toList());
        }
        ListResponseDto<NewsResponseDTO> response = listResponseMapper.setDataListResponse(listDTO, pageEntity, pageable);
        return new BaseResponse(Constant.SUCCESS, "get.list.news", response);
    }

    // update and create.
    // request have id => update
    // update find=>update.
    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public NewsResponseDTO create(NewsRequestDTO request) {
        NewsEntity entity = new NewsEntity();
        entity = (NewsEntity) newsMapper.convertToEntity(request, entity);
        entity.setStatus(Constant.INSERT);
        entity = newsRepository.save(entity);
        NewsResponseDTO response = modelMapper.map(entity, NewsResponseDTO.class);
        return response;
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public NewsResponseDTO delete(Long id) {
        Optional<NewsEntity> optional = newsRepository.findById(id);
        if(!optional.isPresent()){
            throw new ResourceNotFoundException(String.format("News.not.found.with.id:%s",id));
        }
        NewsEntity entity = optional.get();
        entity.setStatus(Constant.DELETE);
        newsRepository.save(entity);
        NewsResponseDTO response = modelMapper.map(entity, NewsResponseDTO.class);
        return response;
    }

}
