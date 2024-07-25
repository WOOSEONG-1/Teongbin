package com.ssafy.teongbin.user.service;

import com.ssafy.teongbin.common.exception.CustomException;
import com.ssafy.teongbin.common.exception.ErrorType;
import com.ssafy.teongbin.common.jwt.PrincipalDetails;
import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.trash.repository.TrashcanRepository;
import com.ssafy.teongbin.user.dto.request.SignUpRequestDto;
import com.ssafy.teongbin.user.entity.User;
import com.ssafy.teongbin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TrashcanRepository trashcanRepository;

    public String signUp (@RequestBody SignUpRequestDto dto) {
        User user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .build();
        userRepository.saveAndFlush(user);
        return "등록 성공";
    }

    public User profile (PrincipalDetails user){
        Optional<User> ou = userRepository.findByEmail(user.getUsername());
        if ( ou.isPresent() )
            return ou.get();
        else {
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
    }

    public List<Trashcan> userTrashcan (PrincipalDetails userIn) {
        Optional<User> ou = userRepository.findByEmail(userIn.getUsername());
        if ( ou.isPresent() ){
            List<Trashcan> olt = trashcanRepository.findByUser(ou.get());
            return olt;
        }
        else {
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
    }
}
