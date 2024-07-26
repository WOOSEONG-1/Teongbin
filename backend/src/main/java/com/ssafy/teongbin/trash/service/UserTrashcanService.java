package com.ssafy.teongbin.trash.service;

import com.ssafy.teongbin.common.exception.CustomException;
import com.ssafy.teongbin.common.exception.ErrorType;
import com.ssafy.teongbin.common.jwt.PrincipalDetails;
import com.ssafy.teongbin.trash.dto.response.UserLogDto;
import com.ssafy.teongbin.trash.dto.response.UserTrashcanDto;
import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.trash.repository.TrashcanRepository;
import com.ssafy.teongbin.user.entity.User;
import com.ssafy.teongbin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserTrashcanService {

    private final UserRepository userRepository;
    private final TrashcanRepository trashcanRepository;

    public List<UserTrashcanDto> userTrashcan (PrincipalDetails userIn) {
        Optional<User> ou = userRepository.findByEmail(userIn.getUsername());
        if ( ou.isPresent() ){
            List<Trashcan> lt = trashcanRepository.findByUser(ou.get());
            List<UserTrashcanDto> utd = lt.stream()
                    .map(UserTrashcanDto::new) // UserTrashcanDto클래스의 생성자를 참조(new : 생성자를 호출)
                    .collect(Collectors.toList());
            return utd;
        }
        else {
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
    }

    public List<UserLogDto> userRestlog (PrincipalDetails userIn) {
        Optional<User> ou = userRepository.findByEmail(userIn.getUsername());
        if ( ou.isPresent() ){
            List<Trashcan> lt = trashcanRepository.findByUser(ou.get());
            List<UserLogDto> uld = lt.stream()
                    .map(UserLogDto::new) // UserLogDto클래스의 생성자를 참조(new : 생성자를 호출)
                    .collect(Collectors.toList());
            return uld;
        }
        else {
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
    }



}
