package com.ssafy.teongbin.trash.service;

import com.ssafy.teongbin.common.exception.CustomException;
import com.ssafy.teongbin.common.exception.ErrorType;
import com.ssafy.teongbin.common.jwt.PrincipalDetails;
import com.ssafy.teongbin.trash.dto.request.NewTrashcanRequest;
import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.trash.repository.TrashcanRepository;
import com.ssafy.teongbin.user.entity.User;
import com.ssafy.teongbin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrashcanService {

    private final TrashcanRepository trashcanRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long join(NewTrashcanRequest newTrashcanRequest, PrincipalDetails userIn) {

        User user;

        Optional<User> ou = userRepository.findByEmail(userIn.getUsername());
        if ( ou.isPresent() )
            user = ou.get();
        else {
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }

        Trashcan trashcan = new Trashcan();
        trashcan.setUser(user);
        trashcan.setSerialNumber(newTrashcanRequest.getSerialNumber());
        trashcan.setNickname(newTrashcanRequest.getNickname());
        trashcan.setLocation(newTrashcanRequest.getLocation());
        trashcanRepository.save(trashcan);
        return trashcan.getId();
    }

    @Transactional
    public void deleteTrashcan(Long trashcanId, PrincipalDetails userIn) {
        Optional<User> ou = userRepository.findByEmail(userIn.getUsername());
        Optional<Trashcan> ot = trashcanRepository.findById(trashcanId);
        if ( ou.isPresent() ) {
            if ( ot.isPresent() ){
                trashcanRepository.deleteById(trashcanId);
            } else {
                throw new CustomException(ErrorType.NOT_FOUND_TRASHCAN);
            }
        }
        else {
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
    }

    @Transactional
    public void update(Long id, String nickname, Point location, PrincipalDetails userIn) {
        Optional<User> ou = userRepository.findByEmail(userIn.getUsername());
        if ( ou.isPresent() ) {
            Trashcan trashcan = trashcanRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("쓰레기통이 존재하지 않습니다."));
            trashcan.setNickname(nickname);
            trashcan.setLocation(location);
        }
        else {
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
    }

    public Trashcan findOne(Long trashcanId) {
        return trashcanRepository.findById(trashcanId).get();
    }
}
