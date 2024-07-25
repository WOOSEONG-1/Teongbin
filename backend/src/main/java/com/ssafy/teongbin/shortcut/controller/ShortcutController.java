package com.ssafy.teongbin.shortcut.controller;

import com.ssafy.teongbin.shortcut.dto.request.AddShortcutRequestDto;
import com.ssafy.teongbin.shortcut.dto.request.DeleteShortcutRequestDto;
import com.ssafy.teongbin.shortcut.dto.response.ShortcutResponseDto;
import com.ssafy.teongbin.shortcut.entity.Shortcut;
import com.ssafy.teongbin.shortcut.service.ShortcutService;
import com.ssafy.teongbin.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user/shortcut")
public class ShortcutController {

    private final ShortcutService shortcutService;


    // 숏컷 등록
    @PostMapping("/new")
    //    사용자 인증 정보는 나중에 넣자.
    public String addShortcut(@RequestBody AddShortcutRequestDto addShortcutRequestDto) {
        String answer = shortcutService.addShortcut(addShortcutRequestDto);
        return answer;
        // 나중에 성공 여부, 에러정보 등을 보내야함.
    }

    // 숏컷 삭제
    @PostMapping("/delete")
    public String deleteShortcut(@RequestBody DeleteShortcutRequestDto deleteShortcutRequestDto) {
        String answer = shortcutService.deleteShortcut(deleteShortcutRequestDto);
         return answer;
    }

    // 숏컷 리스트
    @GetMapping("")
    public List getShortcutList() {
        List<ShortcutResponseDto> shortcuts = shortcutService.getShortcutList();
        return shortcuts;
        // 리스트로 해야할지 어떨지 모르겟음.
    };


}
