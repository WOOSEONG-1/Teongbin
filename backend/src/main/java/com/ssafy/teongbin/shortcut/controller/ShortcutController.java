package com.ssafy.teongbin.shortcut.controller;

import com.ssafy.teongbin.shortcut.dto.request.AddShortcutRequestDto;
import com.ssafy.teongbin.shortcut.dto.request.DeleteShortcutRequestDto;
import com.ssafy.teongbin.shortcut.dto.response.ShortcutResponseDto;
import com.ssafy.teongbin.shortcut.service.ShortcutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user/shortcut")
public class ShortcutController {

    private final ShortcutService shortcutService;

    // JWT 토큰에서 사용자 정보 추출하는 메서드

    // 숏컷 등록
    @PostMapping("/new")
//    public ShortcutResponseDto addShortcut(@RequestHeader("Authorization") String userToken,
//    사용자 인증 정보는 나중에 넣자.
    public String addShortcut(@RequestBody AddShortcutRequestDto addShortcutRequestDto) {
        String answer = shortcutService.addShortcut(addShortcutRequestDto);
        return answer;
        // 나중에 성공 여부, 에러정보 등을 보내야함.
    }

    // 숏컷 삭제
//    @PostMapping("/delete")
//    public String deleteShortcut(@RequestHeader("Authorization") String userToken,
//                                 @RequestBody DeleteShortcutRequestDto dto) {
////        return pass;
//    }
//    // 숏컷 리스트
//    @GetMapping("")
//    public List getShortcutAll(@RequestHeader("Authorization") String userToken) {
//        // 리스트로 해야할지 어떨지 모르겟음.
//    }


}
