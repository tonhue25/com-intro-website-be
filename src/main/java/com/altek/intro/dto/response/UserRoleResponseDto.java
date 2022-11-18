package com.altek.intro.dto.response;

import com.altek.intro.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleResponseDto{

    private Long userRoleId;
    private RoleResponseDto role;

}
