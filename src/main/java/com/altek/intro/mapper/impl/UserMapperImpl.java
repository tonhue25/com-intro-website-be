package com.altek.intro.mapper.impl;

import com.altek.intro.dto.request.UserRequestDto;
import com.altek.intro.entities.Role;
import com.altek.intro.entities.User;
import com.altek.intro.entities.UserRole;
import com.altek.intro.mapper.RoleMapper;
import com.altek.intro.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapperImpl extends AbstractMapperImpl implements UserMapper {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User convertToEntity(User entity, UserRequestDto dto, List<UserRole> list) {
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setStatus(dto.getStatus());
        entity.setPassword(dto.getPassword());
        entity.setUserRoles(list);
        return entity;
    }

}
