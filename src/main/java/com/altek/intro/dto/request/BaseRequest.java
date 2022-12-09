package com.altek.intro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequest<T> {

    @Min(1)
    private Integer size;

    @Min(1)
    private Integer page;
    private String search;
    private String sortType;
    private String sortBy;
    private Long id;
    private List<Long> types;
    private List<String> locations;
    private List<Long> groups;
    private String startDate;
    private String endDate;
    private String language;
    private List<String> enumTypes;
}
