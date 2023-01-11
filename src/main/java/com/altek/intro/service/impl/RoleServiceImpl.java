package com.altek.intro.service.impl;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.RoleResponseDto;
import com.altek.intro.entity.Role;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.mapper.RoleMapper;
import com.altek.intro.repository.RoleRepository;
import com.altek.intro.service.RoleService;
import com.altek.intro.util.Constant;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends AbstractServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public BaseResponse getRoles() {
        try {
            List<Role> listEntity = roleRepository.findAll();
            if (!CollectionUtils.isNotEmpty(listEntity)) {
                return new BaseResponse(Constant.SUCCESS, "get.list.role", Constant.EMPTY_LIST);
            }
            RoleResponseDto responseDto = new RoleResponseDto();
            List<Object> listDto = roleMapper.convertListToDto(listEntity, responseDto);
            ListResponseDto<Object> response = new ListResponseDto<>(listDto);
            return new BaseResponse(Constant.SUCCESS, "get.list.role", response);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "get.list.role", e.getMessage());
        }
    }

    @Override
    public BaseResponse createOrUpdateRole(BaseRequest request) {
        try {
            Role role = new Role();
            Optional<Role> optional = roleRepository.findById(request.getId());
            if (optional.isPresent()) {
                role = optional.get();
            }
            role = modelMapper.map(request, Role.class);
            role = roleRepository.save(role);
            RoleResponseDto response = modelMapper.map(role, RoleResponseDto.class);
            return new BaseResponse(Constant.SUCCESS, "create.or.update.role", response);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "create.or.update.role", e.getMessage());
        }
    }

    @Override
    public BaseResponse deleteRole(Long id) {
        try {
            Optional<Role> optional = roleRepository.findById(id);
            if (!optional.isPresent()) {
                throw new ResourceNotFoundException(String.format("role.not.found.with.id:%s", id));
            }
            Role role = optional.get();
            role.setStatus(Constant.DELETE);
            role = roleRepository.save(role);
            if (role.getStatus() == Constant.DELETE) {
                return new BaseResponse(Constant.SUCCESS, "delete.role");
            }
            return new BaseResponse(Constant.FAIL, "delete.role");
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "delete.role", e.getMessage());
        }
    }
}
