package com.altek.intro.mapper;

import com.altek.intro.entities.Role;
import com.altek.intro.entities.UserRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMapper extends  AbstractMapper{
    List<Role> checkList(List<Long> list);
}
