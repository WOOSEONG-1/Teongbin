package com.ssafy.teongbin.log.dto.response;

import lombok.Data;

@Data
public class CategorylogResponse {
    public CategorylogResponse(Long id) {
        this.id = id;
    }

    private Long id;

}
