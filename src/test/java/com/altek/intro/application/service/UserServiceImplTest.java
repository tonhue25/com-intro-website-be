package com.altek.intro.application.service;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ListResponseDto;
import com.altek.intro.dto.response.RoleResponseDto;
import com.altek.intro.dto.response.UserResponseDto;
import com.altek.intro.entity.Role;
import com.altek.intro.entity.User;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.mapper.RoleMapper;
import com.altek.intro.repository.RoleRepository;
import com.altek.intro.repository.UserRepository;
import com.altek.intro.service.impl.RoleServiceImpl;
import com.altek.intro.service.impl.UserServiceImpl;
import com.altek.intro.util.Constant;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest( { ListResponseDto.class })
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    RoleMapper roleMapper;

    @Test
    void deleteUser_shouldReturnUser_whenUserIdExist() {
        Long id = 1L;
        User entity = mock(User.class);

        when(userRepository.findById(id)).thenReturn(Optional.of(entity));
        when(userRepository.save(entity)).thenReturn(entity);
        Mockito.when(entity.getStatus()).thenReturn(Constant.DELETE);

        BaseResponse actual = userService.deleteUser(id);
        verify(entity).setStatus(Constant.DELETE);
        assertThat(actual.getMessage()).isEqualTo("delete.user");
        assertThat(actual.getHttp_code()).isEqualTo(Constant.SUCCESS);
    }

    @Test
    void deleteUser_shouldThrowsException_whenUserIdExist() {
        Long id = 1L;
        User entity = mock(User.class);

        when(userRepository.findById(id)).thenReturn(Optional.of(entity));
        when(userRepository.save(entity)).thenReturn(entity);
        Mockito.when(entity.getStatus()).thenReturn(Constant.ACTIVE);

        BaseResponse actual = userService.deleteUser(id);
        assertThat(actual.getMessage()).isEqualTo("delete.user");
        assertThat(actual.getHttp_code()).isEqualTo(Constant.FAIL);
    }

    @Test
    void deleteUser_shouldThrowsExceptionNotFound_whenNotFound() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.ofNullable(null));

        BaseResponse actual = userService.deleteUser(id);
        assertThat(actual.getMessage()).isEqualTo("delete.user");
        assertThat(actual.getHttp_code()).isEqualTo(Constant.FAIL);
        assertThat(actual.getData()).isEqualTo(String.format("user.not.found.with.id:%s", id));
    }

    @Test
    void getUser_shouldThrowsExceptionNotFound_whenUserIdNotExist() {
        String username = "an";
        when(userRepository.findByUsername(username)).thenReturn(Optional.ofNullable(null));

        BaseResponse actual = userService.getUser(username);
        assertThat(actual.getMessage()).isEqualTo("get.user.with.username");
        assertThat(actual.getHttp_code()).isEqualTo(Constant.FAIL);
        assertThat(actual.getData()).isEqualTo(String.format("user.not.found.with.username:%s", username));
    }

    @Test
    void getUser_shouldReturnUser_whenUserIdExist() {
        String username = "an";
        User entity = mock(User.class);
        UserResponseDto userResponseDto = mock(UserResponseDto.class);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(entity));
        when(modelMapper.map(entity,UserResponseDto.class)).thenReturn(userResponseDto);

        BaseResponse actual = userService.getUser(username);
        assertThat(actual.getMessage()).isEqualTo(String.format("get.user.with.username:%s", entity.getUsername()));
        assertThat(actual.getHttp_code()).isEqualTo(Constant.SUCCESS);
        assertThat(actual.getData()).isEqualTo(userResponseDto);
    }
}
