package com.ssafy.teongbin.user.controller;

import com.ssafy.teongbin.common.jwt.PrincipalDetails;
import com.ssafy.teongbin.common.reseponse.MsgType;
import com.ssafy.teongbin.common.reseponse.ResponseEntityDto;
import com.ssafy.teongbin.common.reseponse.ResponseUtils;
import com.ssafy.teongbin.user.dto.request.SignUpRequestDto;
import com.ssafy.teongbin.user.entity.User;
import com.ssafy.teongbin.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signUp (@RequestBody SignUpRequestDto dto) {

        return userService.signUp(dto);
    }

    @GetMapping("/profile")
    public ResponseEntityDto<User> profile(@AuthenticationPrincipal PrincipalDetails user){

        return ResponseUtils.ok(userService.profile(user), MsgType.SEARCH_SUCCESSFULLY);
    }
}
