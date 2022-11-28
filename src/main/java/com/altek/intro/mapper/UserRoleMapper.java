package com.altek.intro.mapper;

import com.altek.intro.entity.Role;
import com.altek.intro.entity.User;
import com.altek.intro.entity.UserRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRoleMapper extends  AbstractMapper{
    List<UserRole> checkList(List<Role> ids, User user);
}
