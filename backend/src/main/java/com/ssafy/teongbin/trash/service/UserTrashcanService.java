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
        String username = userIn.getUsername();
        if (username == null || username.trim().isEmpty()) {
            throw new CustomException(ErrorType.NOT_FOUND_USERNAME);
        }
        Optional<User> ou = userRepository.findByEmail(userIn.getUsername());
        if ( ou.isPresent() ){
            List<Trashcan> lt = trashcanRepository.findByUser(ou.get());
            if (lt.isEmpty()) {
                throw new CustomException(ErrorType.NOT_FOUND_TRASHCAN);
            }
            List<UserTrashcanDto> utd = lt.stream()
                    .map(t -> {
                        try {
                            return new UserTrashcanDto(t);
                        } catch (Exception e) {
                            throw new CustomException(ErrorType.NOT_FOUND_REST);
                        }
                    })
                    .collect(Collectors.toList());
            return utd;
        }
        else {
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
    }

    public List<UserLogDto> userRestlog (PrincipalDetails userIn) {
        String username = userIn.getUsername();
        if (username==null || username.trim().isEmpty()) {
            throw new CustomException(ErrorType.NOT_FOUND_USERNAME);
        }
        Optional<User> ou = userRepository.findByEmail(userIn.getUsername());
        if ( ou.isPresent() ){
            List<Trashcan> lt = trashcanRepository.findByUser(ou.get());
            if (lt.isEmpty()) {
                throw new CustomException(ErrorType.NOT_FOUND_TRASHCAN);
            }
            List<UserLogDto> uld = lt.stream()
                    .map(t-> {
                        try {
                            return new UserLogDto(t);
                        } catch (Exception e) {
                            throw new CustomException(ErrorType.NOT_FOUND_REST);
                        }
                    })
                    .collect(Collectors.toList());
            return uld;
        }
        else {
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
    }
}
