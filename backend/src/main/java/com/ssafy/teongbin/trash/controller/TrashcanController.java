package com.ssafy.teongbin.trash.controller;

import com.ssafy.teongbin.trash.dto.request.NewTrashcanRequest;
import com.ssafy.teongbin.trash.dto.response.NewTrashcanResponse;
import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.trash.service.TrashcanService;
import com.ssafy.teongbin.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class TrashcanController {
    private final TrashcanService trashcanService;

    @PostMapping("/trash/new")
    public NewTrashcanResponse newTrashcan(@RequestBody NewTrashcanRequest newTrashcanRequest) {
        Trashcan trashcan = new Trashcan();

//        실제 유저 정보 넣는걸로 수정
        User user = new User();
        trashcan.setUser(user);

        trashcan.setSerial_number(newTrashcanRequest.getSerial_number());
        trashcan.setNickname(newTrashcanRequest.getNickname());
        trashcan.setLocation(newTrashcanRequest.getLocation());
        trashcan.setCreated_at(LocalDateTime.now());
        Long id = trashcanService.join(trashcan);
        return new NewTrashcanResponse(id);
    }
}
