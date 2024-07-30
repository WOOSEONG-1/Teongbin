package com.ssafy.teongbin.user.service;

import com.ssafy.teongbin.common.exception.CustomException;
import com.ssafy.teongbin.common.exception.ErrorType;
import com.ssafy.teongbin.common.jwt.PrincipalDetails;
import com.ssafy.teongbin.user.dto.request.SignUpRequestDto;
import com.ssafy.teongbin.user.dto.request.UpdateUserRequestDto;
import com.ssafy.teongbin.user.entity.User;
import com.ssafy.teongbin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public String signUp (@RequestBody SignUpRequestDto dto) {
        if ( userRepository.findByEmail(dto.getEmail()).isEmpty() ){
            User user = User.builder()
                    .email(dto.getEmail())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .name(dto.getName())
                    .build();
            userRepository.saveAndFlush(user);
        }
        else{
            throw new CustomException(ErrorType.DUPLICATED_USERID);
        }
        return "등록 성공";
    }

    // 회원 정보 조회
    public User profile (PrincipalDetails user){
        Optional<User> ou = userRepository.findByEmail(user.getUsername());
        if ( ou.isPresent() )
            return ou.get();
        else {
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
    }

    // 회원 정보 수정
    public void update (PrincipalDetails user, UpdateUserRequestDto dto ){
        if ( dto.getName() == null )
            throw new CustomException(ErrorType.CONTENT_IS_NULL);
        Optional<User> ou = userRepository.findByEmail(user.getUsername());
        if ( ou.isEmpty() )
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        ou.get().update(dto.getName());
        userRepository.save(ou.get());
    }
}
