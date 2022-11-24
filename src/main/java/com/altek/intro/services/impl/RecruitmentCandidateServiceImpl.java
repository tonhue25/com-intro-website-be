package com.altek.intro.services.impl;

import com.altek.intro.repository.RecruitmentCandidateRepository;
import com.altek.intro.services.RecruitmentCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecruitmentCandidateServiceImpl extends AbstractServiceImpl implements RecruitmentCandidateService {
    @Autowired
    private RecruitmentCandidateRepository recruitmentCandidateRepository;

//    public BaseResponse getList(BaseRequest request) {
//        ListResponseDto<RecruitmentResponseDto> response = new ListResponseDto<>();
//        List<RecruitmentResponseDto> listResponse = new ArrayList<>();
//        List<String> locations = new ArrayList<>();
//        int pageNumber = 1;
//        int size = 0;
//        int recordPerPage = 0;
//        int totalPages = 1;
//        List<Long> types = new ArrayList<>();
//        List<Long> groups = new ArrayList<>();
//        if (DataUtil.isEmpty(request.getLocations())) {
//            locations = Arrays.asList(Constant.HCM, Constant.HN , Constant.HCMC);
//        } else {
//            locations = request.getLocations();
//        }
//        if (DataUtil.isEmpty(request.getTypes())) {
//            types = recruitmentTypeRepository.findAll().stream()
//                    .map(RecruitmentType::getId)
//                    .collect(Collectors.toList());
//        } else {
//            types = request.getTypes();
//        }
//        if (DataUtil.isEmpty(request.getGroups())) {
//            groups = productGroupRepository.findAll().stream()
//                    .map(ProductGroup::getId)
//                    .collect(Collectors.toList());
//        } else {
//            groups = request.getGroups();
//        }
//        if (DataUtil.isEmpty(request.getPage()) || DataUtil.isEmpty(request.getSize())) {
//            listResponse = recruitmentRepository.getList(request.getSearch(), request.getLanguage(), locations, types, groups);
//            size = recordPerPage = listResponse.size();
//        } else {
//            Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());
//            if (!DataUtil.isEmpty(request.getSortBy()) && !DataUtil.isEmpty(request.getSortType())) {
//                Sort.Direction sort = Sort.Direction.ASC;
//                if (request.getSortType().equals("DESC")) {
//                    sort = Sort.Direction.DESC;
//                }
//                pageable = PageRequest.of(request.getPage() - 1, request.getSize(),
//                        Sort.by(sort, request.getSortBy()));
//            }
//            Page<RecruitmentResponseDto> pageEntity = recruitmentRepository.getList(request.getSearch(), request.getLanguage(), locations, types, groups, pageable);
//            listResponse = pageEntity.getContent();
//            size = pageEntity.getNumberOfElements();
//            recordPerPage = pageable.getPageSize();
//            totalPages = pageEntity.getTotalPages();
//            pageNumber = pageable.getPageNumber();
//            if (pageEntity.getTotalPages() > 0) {
//                pageNumber = pageNumber + 1;
//            }
//        }
//        response.setLanguage(request.getLanguage());
//        response.setList(listResponse);
//        response.setPage(pageNumber);
//        response.setSize(size);
//        response.setTotalPages(totalPages);
//        response.setRecordPerPage(recordPerPage);
//        return new BaseResponse(Constant.SUCCESS, "get.list.recruitment", response);
//    }
}
