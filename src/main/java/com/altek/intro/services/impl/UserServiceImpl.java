package com.altek.intro.services.impl;

import com.altek.intro.dto.request.UserRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.UserResponseDto;
import com.altek.intro.entities.Role;
import com.altek.intro.entities.User;
import com.altek.intro.entities.UserRole;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.RoleMapper;
import com.altek.intro.mapper.UserMapper;
import com.altek.intro.mapper.UserRoleMapper;
import com.altek.intro.repository.RoleRepository;
import com.altek.intro.repository.UserRepository;
import com.altek.intro.repository.UserRoleRepository;
import com.altek.intro.services.UserService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.DataUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public BaseResponse createUser(UserRequestDto request) {
        User user = new User();
        List<Role> listRole = new ArrayList<>();
        List<UserRole> list = new ArrayList<>();
        if (!DataUtil.isEmpty(request.getRoles())) {
            List<Long> ids = request.getRoles();
            listRole = roleMapper.checkList(ids);
        }
        if (!DataUtil.isEmpty(request.getPassword())) {
            request.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (!DataUtil.isEmpty(request.getId())) {
            Optional<User> optional = userRepository.findById(request.getId());
            if (optional.isPresent()) {
                user = optional.get();
                list = optional.get().getUserRoles();
            }
        }
        user = modelMapper.map(request, User.class);
        user = userRepository.save(user);
        list = userRoleMapper.checkList(listRole, user);
        UserResponseDto response = modelMapper.map(user, UserResponseDto.class);
        return new BaseResponse(Constant.SUCCESS, String.format("add.or.update.user.with.id:%s", user.getId()),
                response);
    }
    @Override
    public BaseResponse deleteUser(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("user.not.found.with.id:%s", id));
        }
        User entity = optional.get();
        if (entity.getStatus().equals(Constant.DELETE)) {
            return new BaseResponse(Constant.SUCCESS, String.format("user.already.delete.with.id:%s", id),
                    String.format("status.of.user:%s", entity.getStatus()));
        }
        entity.setStatus(Constant.DELETE);
        entity = userRepository.save(entity);
        return new BaseResponse(Constant.SUCCESS, String.format("delete.user.with.id:%s", id),
                String.format("status.of.user:%s", entity.getStatus()));
    }

    @Override
    public BaseResponse getUser(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException(String.format("user.not.found.with.id:%s", id));
        }
        User user = optional.get();
        UserResponseDto response = modelMapper.map(user, UserResponseDto.class);
        return new BaseResponse(Constant.SUCCESS, String.format("get.user.with.id:%s", user.getId()),
                response);
    }
}
