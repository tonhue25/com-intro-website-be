package com.altek.intro.application.service;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.RoleResponseDto;
import com.altek.intro.entity.Role;
import com.altek.intro.mapper.RoleMapper;
import com.altek.intro.repository.RoleRepository;
import com.altek.intro.service.impl.RoleServiceImpl;
import com.altek.intro.util.Constant;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTestImpl {

    @Mock
    RoleRepository roleRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    RoleServiceImpl roleService;

    @Mock
    RoleMapper roleMapper;

    @Test
    void deleteRole_shouldReturnRole_whenRoleIdExist() {
        Long id = 1L;
        Role entity = mock(Role.class);

        when(roleRepository.findById(id)).thenReturn(Optional.of(entity));
        when(roleRepository.save(entity)).thenReturn(entity);
        Mockito.when(entity.getStatus()).thenReturn(Constant.DELETE);

        BaseResponse actual = roleService.deleteRole(id);

        verify(entity).setStatus(Constant.DELETE);
        assertThat(actual.getMessage()).isEqualTo("delete.role");
        assertThat(actual.getHttp_code()).isEqualTo(Constant.SUCCESS);
    }

    @Test
    void deleteRole_shouldThrowsException_whenRoleIdExist() {
        Long id = 1L;
        Role entity = mock(Role.class);
        when(roleRepository.findById(id)).thenReturn(Optional.of(entity));
        when(roleRepository.save(entity)).thenReturn(entity);
        Mockito.when(entity.getStatus()).thenReturn(Constant.ACTIVE);
        BaseResponse actual = roleService.deleteRole(id);

        assertThat(actual.getMessage()).isEqualTo("delete.role");
        assertThat(actual.getHttp_code()).isEqualTo(Constant.FAIL);
    }

    @Test
    void deleteRole_shouldThrowsExceptionNotFound_whenNotFound() {
        Long id = 1L;
        when(roleRepository.findById(id)).thenReturn(Optional.ofNullable(null));
        BaseResponse actual = roleService.deleteRole(id);

        assertThat(actual.getMessage()).isEqualTo("delete.role");
        assertThat(actual.getHttp_code()).isEqualTo(Constant.FAIL);
        assertThat(actual.getData()).isEqualTo(String.format("role.not.found.with.id:%s", id));
    }

    @Test
    void createOrUpdateRole_shouldReturnUpdateRole_whenGivenValidRequestDto() {
        Role entity = mock(Role.class);
        BaseRequest baseRequest = mock(BaseRequest.class);
        RoleResponseDto expect = mock(RoleResponseDto.class);

        when(roleRepository.findById(baseRequest.getId())).thenReturn(Optional.of(entity));
        when(modelMapper.map(baseRequest, Role.class)).thenReturn(entity);
        when(roleRepository.save(entity)).thenReturn(entity);

        when(modelMapper.map(entity, RoleResponseDto.class)).thenReturn(expect);
        BaseResponse actual = roleService.createOrUpdateRole(baseRequest);
        assertThat(actual.getMessage()).isEqualTo("create.or.update.role");
        assertThat(actual.getHttp_code()).isEqualTo(Constant.SUCCESS);
        assertThat(actual.getData()).isEqualTo(expect);
    }

    @Test
    void createOrUpdateRole_shouldReturnNewRole_whenGivenValidRequestDto() {
        Role entity = mock(Role.class);
        BaseRequest baseRequest = mock(BaseRequest.class);
        RoleResponseDto expect = mock(RoleResponseDto.class);

        when(roleRepository.findById(baseRequest.getId())).thenReturn(Optional.ofNullable(null));
        when(modelMapper.map(baseRequest, Role.class)).thenReturn(entity);
        when(roleRepository.save(entity)).thenReturn(entity);

        when(modelMapper.map(entity, RoleResponseDto.class)).thenReturn(expect);
        BaseResponse actual = roleService.createOrUpdateRole(baseRequest);
        assertThat(actual.getMessage()).isEqualTo("create.or.update.role");
        assertThat(actual.getHttp_code()).isEqualTo(Constant.SUCCESS);
        assertThat(actual.getData()).isEqualTo(expect);
    }

    @Test
    void getRoles_shouldReturnRoles_whenListIsNull() {
        List<Role> roles = mock(List.class);
        when(roleRepository.findAll()).thenReturn(roles);
        Mockito.when(!CollectionUtils.isNotEmpty(roles)).thenReturn(true);
        BaseResponse actual = roleService.getRoles();
        assertThat(actual.getMessage()).isEqualTo("get.list.role");
        assertThat(actual.getHttp_code()).isEqualTo(Constant.SUCCESS);
        assertThat(actual.getData()).isEqualTo(Constant.EMPTY_LIST);
    }
}
