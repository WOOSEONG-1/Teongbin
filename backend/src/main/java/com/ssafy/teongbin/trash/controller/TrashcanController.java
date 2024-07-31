package com.ssafy.teongbin.trash.controller;

import com.ssafy.teongbin.common.jwt.PrincipalDetails;
import com.ssafy.teongbin.common.reseponse.MsgType;
import com.ssafy.teongbin.common.reseponse.ResponseEntityDto;
import com.ssafy.teongbin.common.reseponse.ResponseUtils;
import com.ssafy.teongbin.trash.dto.request.NewTrashcanRequest;
import com.ssafy.teongbin.trash.dto.request.UpdateTrashcanRequest;
import com.ssafy.teongbin.trash.dto.response.*;
import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.trash.repository.TrashcanRepository;
import com.ssafy.teongbin.trash.service.TrashcanTestService;
import com.ssafy.teongbin.trash.service.TrashcanService;
import com.ssafy.teongbin.trash.service.UserTrashcanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trash")
public class TrashcanController {
    private final TrashcanService trashcanService;
    private final TrashcanTestService trashcanTestService;
    private final TrashcanRepository trashcanRepository;
    private final UserTrashcanService userTrashcanService;

    @PostMapping("/new")
    public NewTrashcanResponse newTrashcan(
            @RequestBody NewTrashcanRequest newTrashcanRequest,
            @AuthenticationPrincipal PrincipalDetails userIn) {
        Long id = trashcanService.join(newTrashcanRequest, userIn);
        return new NewTrashcanResponse(id);
    }

    @PostMapping("/{trashcan_id}/delete")
    public String deleteTrash(@PathVariable Long trashcan_id,
                              @AuthenticationPrincipal PrincipalDetails userIn) {
        trashcanService.deleteTrashcan(trashcan_id, userIn);
        return "Deleted trashcan with ID: " + trashcan_id;
    }

    @PostMapping("/{trashcan_id}/update")
    public UpdateTrashcanResponse UpdateTrashcan(
            @PathVariable("trashcan_id") Long id,
            @RequestBody @Valid UpdateTrashcanRequest request,
            @AuthenticationPrincipal PrincipalDetails userIn) {
        trashcanService.update(id, request.getNickname(),request.getLocation(), userIn);
        Trashcan findTrashcan = trashcanService.findOne(id);
        return new UpdateTrashcanResponse(findTrashcan.getId(), findTrashcan.getNickname(),findTrashcan.getLocation());
    }

    @GetMapping("/user/trashcan")
    public ResponseEntityDto<List<UserTrashcanDto>> userTrashcan(@AuthenticationPrincipal PrincipalDetails user) {
        return ResponseUtils.ok(userTrashcanService.userTrashcan(user), MsgType.SEARCH_TRASH_LIST_SUCCESSFULLY);
    }

    @GetMapping("/user/restlog")
    public ResponseEntityDto<List<UserLogDto.RestDto>> userRestlog(@AuthenticationPrincipal PrincipalDetails user) {
        return ResponseUtils.ok(userTrashcanService.userRestlog(user), MsgType.SEARCH_REST_LIST_SUCCESSFULLY);
    }

    @GetMapping("/{trashcan_id}/catlog")
    public ResponseEntityDto<List<TrashcanCatlogDto>> TrashcanCatlog(
            @AuthenticationPrincipal PrincipalDetails user,
            @PathVariable("trashcan_id") Long trashcanId) {
        return ResponseUtils.ok(userTrashcanService.trashcanCatlog(user, trashcanId), MsgType.SEARCH_CAT_LOG_SUCCESSFULLY);
    }

    //명세서에 없음
    //그냥 모든 쓰레기통 확인하고싶어서 만들어봄
    @GetMapping("/all")
    public List<Trashcan> allTrashcan() {
        return trashcanTestService.findTrashcans();
    }
}
