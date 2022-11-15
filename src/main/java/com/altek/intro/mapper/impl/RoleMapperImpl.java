package com.altek.intro.mapper.impl;

import com.altek.intro.entities.Role;
import com.altek.intro.entities.UserRole;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.RoleMapper;
import com.altek.intro.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RoleMapperImpl extends AbstractMapperImpl implements RoleMapper {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> checkList(List<Long> ids) {
        List<Role> response = new ArrayList<>();
        for (Long id : ids) {
            Optional<Role> optional = roleRepository.findById(id);
            if (!optional.isPresent()) {
                throw new ResourceNotFoundException(String.format("role.not.found.with.id:%s", id));
            }
            response.add(optional.get());
        }
        return response;
    }
}
