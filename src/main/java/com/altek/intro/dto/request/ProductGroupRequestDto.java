package com.altek.intro.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductGroupRequestDto {
    private Long id;
    private Integer status;
    @NotNull(message = "name is mandatory, unique")
    private String name;
}
