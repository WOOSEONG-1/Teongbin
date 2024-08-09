package com.ssafy.teongbin.mail.service;

import com.ssafy.teongbin.common.exception.CustomException;
import com.ssafy.teongbin.common.exception.ErrorType;
import com.ssafy.teongbin.common.jwt.PrincipalDetails;
import com.ssafy.teongbin.mail.dto.ApproveRequestDto;
import com.ssafy.teongbin.user.entity.User;
import com.ssafy.teongbin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MailVerificationService {

    private final RedisService redisService;
    private final UserRepository userRepository;

    // 인증 코드 검증 메소드
    public boolean verifyCode(ApproveRequestDto approveRequestDto, PrincipalDetails userIn) {
        User user;
        Optional<User> ou = userRepository.findByEmail(userIn.getUsername());
        if ( !ou.isPresent() ){
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
        String storedCode = redisService.getVerificationCode(approveRequestDto.getEmail());
        if (storedCode != null && storedCode.equals(approveRequestDto.getCode())) {
            redisService.deleteVerificationCode(approveRequestDto.getEmail());
            return true;
        } else {
            return false;
        }
    }
}