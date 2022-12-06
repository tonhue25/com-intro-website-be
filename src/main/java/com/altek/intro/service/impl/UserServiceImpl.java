package com.altek.intro.service.impl;

import com.altek.intro.dto.request.UserRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.UserResponseDto;
import com.altek.intro.entity.Role;
import com.altek.intro.entity.User;
import com.altek.intro.entity.UserRole;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.mapper.RoleMapper;
import com.altek.intro.mapper.UserMapper;
import com.altek.intro.mapper.UserRoleMapper;
import com.altek.intro.repository.RoleRepository;
import com.altek.intro.repository.UserRepository;
import com.altek.intro.repository.UserRoleRepository;
import com.altek.intro.service.UserService;
import com.altek.intro.util.Constant;
import com.altek.intro.util.DataUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public BaseResponse createUser(UserRequestDto request) {
        try {
            User user = new User();
            List<Role> roles = new ArrayList<>();
            List<UserRole> userRoles = new ArrayList<>();
            if (!DataUtil.isEmpty(request.getRoles())) {
                List<Long> ids = request.getRoles();
                roles = roleMapper.checkList(ids);
            }
            if (!DataUtil.isEmpty(request.getPassword())) {
                request.setPassword(passwordEncoder.encode(request.getPassword()));
            }
            if (!DataUtil.isEmpty(request.getId())) {
                Optional<User> optional = userRepository.findById(request.getId());
                if (optional.isPresent()) {
                    user = optional.get();
                    userRoles = optional.get().getUserRoles();
                }
            }
            user = modelMapper.map(request, User.class);
            user = userRepository.save(user);
            userRoles = userRoleMapper.checkList(roles, user);
            UserResponseDto response = modelMapper.map(user, UserResponseDto.class);
            return new BaseResponse(Constant.SUCCESS, String.format("add.or.update.user.with.id:%s", user.getId()),
                    response);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "add.or.update.user", e.getMessage());
        }
    }

    @Override
    public BaseResponse deleteUser(Long id) {
        try {
            Optional<User> optional = userRepository.findById(id);
            if (!optional.isPresent()) {
                throw new ResourceNotFoundException(String.format("user.not.found.with.id:%s", id));
            }
            User entity = optional.get();
            entity.setStatus(Constant.DELETE);
            entity = userRepository.save(entity);
            if (entity.getStatus() == Constant.DELETE) {
                return new BaseResponse(Constant.SUCCESS, "delete.user");
            }
            return new BaseResponse(Constant.FAIL, "delete.user");
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "delete.user", e.getMessage());
        }
    }

    @Override
    public BaseResponse getUser(String username) {
        try {
            Optional<User> optional = userRepository.findByUsername(username);
            if (!optional.isPresent()) {
                throw new ResourceNotFoundException(String.format("user.not.found.with.username:%s", username));
            }
            User user = optional.get();
            UserResponseDto response = modelMapper.map(user, UserResponseDto.class);
            return new BaseResponse(Constant.SUCCESS, String.format("get.user.with.username:%s", user.getUsername()),
                    response);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "get.user.with.username", e.getMessage());
        }
    }
}
