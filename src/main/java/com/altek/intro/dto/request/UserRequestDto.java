package com.altek.intro.dto.request;

import com.altek.intro.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private Long id;
    private Integer status;
    private String username;
    private String password;
    private String email;
    private List<Long> roles;
}
