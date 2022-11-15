package com.altek.intro.mapper;

import com.altek.intro.dto.request.UserRequestDto;
import com.altek.intro.entities.Role;
import com.altek.intro.entities.User;
import com.altek.intro.entities.UserRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper extends  AbstractMapper{
    User convertToEntity(User entity, UserRequestDto dto, List<UserRole> ids);
}
