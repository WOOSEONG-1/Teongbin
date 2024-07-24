package com.ssafy.teongbin.trash.controller;

import com.ssafy.teongbin.trash.dto.request.NewTrashcanRequest;
import com.ssafy.teongbin.trash.dto.response.NewTrashcanResponse;
import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.trash.service.TrashcanService;
import com.ssafy.teongbin.user.entity.User;
import com.ssafy.teongbin.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class TrashcanController {
    private final TrashcanService trashcanService;
    private final UserService userService;

    @PostMapping("/api/v1/trash/new")
    public NewTrashcanResponse newTrashcan(@RequestBody NewTrashcanRequest newTrashcanRequest) {
        Trashcan trashcan = new Trashcan();

//        실제 유저 정보 넣는걸로 수정
        User user = new User();
        user.setEmail("asdfasd");
        user.setPassword("Asdfasdf");
        userService.join(user);

        trashcan.setSerial_number(newTrashcanRequest.getSerial_number());
        trashcan.setNickname(newTrashcanRequest.getNickname());
        trashcan.setLocation(newTrashcanRequest.getLocation());
        Long id = trashcanService.join(trashcan);
        return new NewTrashcanResponse(id);
    }

    @PostMapping("/trash/{trashcan_id}/delete")
    public String deleteTrash(@PathVariable Long trashcan_id) {
        trashcanService.deleteTrashcan(trashcan_id);
        return "Deleted trashcan with ID: " + trashcan_id;
    }
}
