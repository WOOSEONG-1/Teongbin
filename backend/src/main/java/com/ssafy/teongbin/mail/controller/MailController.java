package com.ssafy.teongbin.mail.controller;

import com.ssafy.teongbin.common.jwt.PrincipalDetails;
import com.ssafy.teongbin.mail.dto.MailDto;
import com.ssafy.teongbin.mail.dto.request.ApproveRequestDto;
import com.ssafy.teongbin.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @ResponseBody
    @PostMapping("/api/v1/user/mail")
    public String MailSend(
            @RequestBody MailDto mailDto,
            @AuthenticationPrincipal PrincipalDetails userIn) {
        int number = mailService.sendMail(mailDto, userIn);
        String num = "" + number;
        return num;
    }

    @PostMapping("/api/v1/user/code")
    public boolean ApproveResponse(@RequestBody ApproveRequestDto approveRequestDto,
                                   @AuthenticationPrincipal PrincipalDetails userIn) {
        boolean result = mailService.approve(approveRequestDto,userIn);
        return result;
    }
}
