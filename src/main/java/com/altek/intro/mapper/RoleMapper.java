package com.altek.intro.mapper;

import com.altek.intro.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMapper extends  AbstractMapper{
    List<Role> checkList(List<Long> list);
}
