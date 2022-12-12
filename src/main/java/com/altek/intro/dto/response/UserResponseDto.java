package com.altek.intro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto extends AbstractResponseDto {

    List<UserRoleResponseDto> userRoles;
    private String username;
    private String email;
}
