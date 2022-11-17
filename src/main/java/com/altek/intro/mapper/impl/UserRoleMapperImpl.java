package com.altek.intro.mapper.impl;

import com.altek.intro.entities.Role;
import com.altek.intro.entities.User;
import com.altek.intro.entities.UserRole;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.RoleMapper;
import com.altek.intro.mapper.UserRoleMapper;
import com.altek.intro.repository.RoleRepository;
import com.altek.intro.repository.UserRoleRepository;
import com.altek.intro.utils.Constant;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserRoleMapperImpl extends AbstractMapperImpl implements UserRoleMapper {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRole> checkList(List<Role> ids, User user) {
        List<UserRole> response = new ArrayList<>();
        for (Role role : ids) {
            UserRole userRole = new UserRole();
            Optional<UserRole> optional = userRoleRepository.findByRoleAndUser(role, user);
            if (!optional.isPresent()) {
                userRole.setStatus(user.getStatus());
                userRole.setUser(user);
                userRole.setRole(role);
                userRole = userRoleRepository.save(userRole);
            } else {
                userRole = optional.get();
            }
            response.add(userRole);
        }
        List<UserRole> list = userRoleRepository.findByUser(user);
        list.removeIf(item -> response.contains(item));
        for (UserRole item : list) {
            item.setStatus(Constant.DELETE);
            userRoleRepository.save(item);
        }
        return response;
    }
}
