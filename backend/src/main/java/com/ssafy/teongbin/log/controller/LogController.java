package com.ssafy.teongbin.log.controller;

import com.ssafy.teongbin.log.dto.request.RestlogRequest;
import com.ssafy.teongbin.log.dto.response.RestlogResponse;
import com.ssafy.teongbin.log.entity.Restlog;
import com.ssafy.teongbin.log.repository.RestlogService;
import com.ssafy.teongbin.trash.dto.request.NewTrashcanRequest;
import com.ssafy.teongbin.trash.dto.response.NewTrashcanResponse;
import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.trash.repository.TrashcanRepository;
import com.ssafy.teongbin.user.entity.User;
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
        Trashcan findTrashcan = trashcanRepository.findById(request.serial_number);
        Restlog restlog = new Restlog();
        restlog.setTrashcan(findTrashcan);
        restlog.setRest_percent(request.getpercent());
        Long id = restlogService.join(restlog);
        return new RestlogResponse(id);
    }
}
