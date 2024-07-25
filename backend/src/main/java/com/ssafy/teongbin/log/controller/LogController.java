package com.ssafy.teongbin.log.controller;

import com.ssafy.teongbin.log.dto.request.CategorylogRequest;
import com.ssafy.teongbin.log.dto.request.RestlogRequest;
import com.ssafy.teongbin.log.dto.response.CategorylogResponse;
import com.ssafy.teongbin.log.dto.response.RestlogResponse;
import com.ssafy.teongbin.log.entity.Restlog;
import com.ssafy.teongbin.log.service.CategorylogService;
import com.ssafy.teongbin.log.service.RestlogService;
import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.trash.repository.TrashcanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogController {
    private final RestlogService restlogService;
    private final CategorylogService categorylogService;

    @PostMapping("/api/v1/trash/rest")
    public RestlogResponse newRestlog(@RequestBody RestlogRequest request) {
        Long id = restlogService.join(request.getSerialNumber(), request.getRestPercent());
        return new RestlogResponse(id);
    }

    @PostMapping("/api/v1/trash/catlog")
    public CategorylogResponse newCategorylog(@RequestBody CategorylogRequest request) {
        Long id = categorylogService.join(request.getSerialNumber(), request.getCategoryId());
        return new CategorylogResponse(id);
    }
}
