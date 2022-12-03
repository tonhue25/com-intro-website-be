package com.altek.intro.dto.response;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListResponseDto<D> {
    private Integer size;
    private Integer page;
    private Integer totalPages;
    private Integer recordPerPage;
    private String language;
    private List<D> list;

    public ListResponseDto(List<D> list) {
        this.list = list;
        this.size = list.size();
        this.page = 1;
        this.totalPages = 1;
        this.recordPerPage = list.size();
    }

    public ListResponseDto(List<D> list, String language) {
        this.list = list;
        this.size = list.size();
        this.page = 1;
        this.totalPages = 1;
        this.recordPerPage = list.size();
        this.language = language;
    }

    public ListResponseDto(Pageable pageable, Page page, List<D> list, String language) {
        this.size = page.getNumberOfElements();
        if (page.getTotalPages() > 0) {
            this.page = pageable.getPageNumber() + 1;
        }
        this.totalPages = page.getTotalPages();
        this.recordPerPage = pageable.getPageSize();
        this.list = list;
        this.language = language;
    }
}
