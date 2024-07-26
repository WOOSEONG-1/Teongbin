package com.ssafy.teongbin.log.controller;

import com.ssafy.teongbin.log.dto.request.CatlogRequest;
import com.ssafy.teongbin.log.dto.response.CatlogResponse;
import com.ssafy.teongbin.log.service.CatlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CatlogController {

    private final CatlogService catlogService;

    @PostMapping("/api/v1/trash/catlog")
    public CatlogResponse newCatlog(@RequestBody CatlogRequest request) {
        Long id = catlogService.join(request.getSerialNumber(), request.getCategoryId());
        return new CatlogResponse(id);
    }
}
