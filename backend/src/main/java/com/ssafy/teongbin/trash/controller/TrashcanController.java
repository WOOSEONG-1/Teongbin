package com.ssafy.teongbin.trash.controller;

import com.ssafy.teongbin.common.jwt.PrincipalDetails;
import com.ssafy.teongbin.trash.dto.request.NewTrashcanRequest;
import com.ssafy.teongbin.trash.dto.request.UpdateTrashcanRequest;
import com.ssafy.teongbin.trash.dto.response.NewTrashcanResponse;
import com.ssafy.teongbin.trash.dto.response.UpdateTrashcanResponse;
import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.trash.service.TrashcanTestService;
import com.ssafy.teongbin.trash.service.TrashcanService;
import com.ssafy.teongbin.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrashcanController {
    private final TrashcanService trashcanService;
    private final TrashcanTestService trashcanTestService;
    private final UserRepository userRepository;

    @PostMapping("/api/v1/trash/new")
    public NewTrashcanResponse newTrashcan(
            @RequestBody NewTrashcanRequest newTrashcanRequest,
            @AuthenticationPrincipal PrincipalDetails userIn) {
        Long id = trashcanService.join(newTrashcanRequest, userIn);
        return new NewTrashcanResponse(id);
    }

    @PostMapping("/api/v1/trash/{trashcan_id}/delete")
    public String deleteTrash(@PathVariable Long trashcan_id,
                              @AuthenticationPrincipal PrincipalDetails userIn) {
        trashcanService.deleteTrashcan(trashcan_id, userIn);
        return "Deleted trashcan with ID: " + trashcan_id;
    }

    @PostMapping("/api/v1/trash/{trashcan_id}/update")
    public UpdateTrashcanResponse UpdateTrashcan(
            @PathVariable("trashcan_id") Long id,
            @RequestBody @Valid UpdateTrashcanRequest request,
            @AuthenticationPrincipal PrincipalDetails userIn) {
        trashcanService.update(id, request.getNickname(),request.getLocation(), userIn);
        Trashcan findTrashcan = trashcanService.findOne(id);
        return new UpdateTrashcanResponse(findTrashcan.getId(), findTrashcan.getNickname(),findTrashcan.getLocation());
    }

    //명세서에 없음
    //그냥 모든 쓰레기통 확인하고싶어서 만들어봄
    @GetMapping("/api/v1/trash/all")
    public List<Trashcan> allTrashcan() {
        return trashcanTestService.findTrashcans();
    }
}
