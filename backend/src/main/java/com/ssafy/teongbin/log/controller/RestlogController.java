package com.ssafy.teongbin.log.controller;

import com.ssafy.teongbin.log.dto.request.RestlogRequest;
import com.ssafy.teongbin.log.dto.response.RestlogResponse;
import com.ssafy.teongbin.log.service.RestlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestlogController {
    private final RestlogService restlogService;

    @PostMapping("/api/v1/trash/rest")
    public RestlogResponse newRestlog(@RequestBody RestlogRequest request) {
        Long id = restlogService.join(request);
        return new RestlogResponse(id);
    }
}
