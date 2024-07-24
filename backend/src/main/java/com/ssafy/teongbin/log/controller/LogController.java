package com.ssafy.teongbin.log.controller;

import com.ssafy.teongbin.log.dto.request.RestlogRequest;
import com.ssafy.teongbin.log.dto.response.RestlogResponse;
import com.ssafy.teongbin.log.entity.Restlog;
import com.ssafy.teongbin.log.repository.RestlogService;
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
    private final TrashcanRepository trashcanRepository;

    @PostMapping("/api/v1/trash/rest")
    public RestlogResponse newRestlog(@RequestBody RestlogRequest request) {
        Trashcan findTrashcan = trashcanRepository.findBySerialNumber(request.getSerial_number());
        Restlog restlog = new Restlog();
        restlog.setTrashcan(findTrashcan);
        restlog.setRest_percent(request.getRest_percent());
        Long id = restlogService.join(restlog);
        return new RestlogResponse(id);
    }
}
