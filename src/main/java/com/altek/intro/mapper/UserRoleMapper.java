package com.altek.intro.mapper;

import com.altek.intro.entities.Role;
import com.altek.intro.entities.User;
import com.altek.intro.entities.UserRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRoleMapper extends  AbstractMapper{
    List<UserRole> checkList(List<Role> ids, User user);
}
