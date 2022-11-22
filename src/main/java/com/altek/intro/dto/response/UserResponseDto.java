package com.altek.intro.dto.response;

import com.altek.intro.entities.Role;
import com.altek.intro.entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto extends  AbstractResponseDto{

    private String username;
    private String email;
    List<UserRoleResponseDto> userRoles;
}