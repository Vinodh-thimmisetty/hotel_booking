package com.thoughtclan.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author thimmv
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomPagination {
    private String sortBy, sortDirection;
    private Integer pageNumber, pageSize;
}