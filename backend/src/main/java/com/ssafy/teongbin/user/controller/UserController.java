package com.ssafy.teongbin.user.controller;

import com.ssafy.teongbin.user.dto.request.NewUserRequest;
import com.ssafy.teongbin.user.dto.response.NewUserResponse;
import com.ssafy.teongbin.user.entity.User;
import com.ssafy.teongbin.user.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/v1/user/signup")
    public NewUserResponse NewUser(@RequestBody @Valid NewUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        Long id = userService.join(user);
        return new NewUserResponse(id);
    }

}
