package io.davidarchanjo.model.dto;

import lombok.Data;

@Data
public class PaginationDTO {
    private int page;
    private int size;
}