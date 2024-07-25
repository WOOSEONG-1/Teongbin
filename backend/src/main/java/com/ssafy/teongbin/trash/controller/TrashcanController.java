package com.ssafy.teongbin.trash.controller;

import com.ssafy.teongbin.trash.dto.request.NewTrashcanRequest;
import com.ssafy.teongbin.trash.dto.request.UpdateTrashcanRequest;
import com.ssafy.teongbin.trash.dto.response.NewTrashcanResponse;
import com.ssafy.teongbin.trash.dto.response.UpdateTrashcanResponse;
import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.trash.service.TrashcanTestService;
import com.ssafy.teongbin.trash.service.TrashcanService;
import com.ssafy.teongbin.user.entity.User;
import com.ssafy.teongbin.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrashcanController {
    private final TrashcanService trashcanService;
    private final UserService userService;
    private final TrashcanTestService trashcanTestService;

    @PostMapping("/api/v1/trash/new")
    public NewTrashcanResponse newTrashcan(@RequestBody NewTrashcanRequest newTrashcanRequest) {

        /**
         실제 유저 조회해서 넣는걸로 수정
         **/

        Trashcan trashcan = new Trashcan();
        trashcan.setUser(user);
        trashcan.setSerialNumber(newTrashcanRequest.getSerialNumber());
        trashcan.setNickname(newTrashcanRequest.getNickname());
        trashcan.setLocation(newTrashcanRequest.getLocation());
        Long id = trashcanService.join(trashcan);
        return new NewTrashcanResponse(id);
    }


    /**
        Spring security 사용자 인증해야함
        @PreAuthorize("hasRole('ROLE_USER') and @securityService.canModifyItem(#itemId)")
     **/
    @PostMapping("/api/v1/trash/{trashcan_id}/delete")
    public String deleteTrash(@PathVariable Long trashcan_id) {
        trashcanService.deleteTrashcan(trashcan_id);
        return "Deleted trashcan with ID: " + trashcan_id;
    }

    /**
     Spring security 사용자 인증해야함
     @PreAuthorize("hasRole('ROLE_USER') and @securityService.canModifyItem(#itemId)")
     **/
    @PostMapping("/api/v1/trash/{trashcan_id}/update")
    public UpdateTrashcanResponse UpdateTrashcan(
            @PathVariable("trashcan_id") Long id,
            @RequestBody @Valid UpdateTrashcanRequest request) {
        trashcanService.update(id, request.getNickname(),request.getLocation());
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
