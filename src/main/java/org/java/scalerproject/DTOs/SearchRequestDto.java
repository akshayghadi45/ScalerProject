package org.java.scalerproject.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {
    private String query;
    private int pageNumber;
    private int pageSize;
    private String sortParam;
}
