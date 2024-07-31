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
        if ( ou.isPresent() ) {
            user = ou.get();
            System.out.println("user = " + user);

        } else {
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }

        Trashcan trashcan = new Trashcan();
        trashcan.setUser(user);
        String sn = newTrashcanRequest.getSerialNumber();
        if ( sn.isEmpty() ) {
            throw new CustomException(ErrorType.NOT_FOUND_SERIAL);
        }
        trashcan.setSerialNumber(sn);
        String nn = newTrashcanRequest.getNickname();
        if ( nn.isEmpty() ) {
            throw new CustomException(ErrorType.NOT_FOUND_NICKNAME);
        }
        trashcan.setNickname(nn);
        double x = newTrashcanRequest.getLocation().getX();
        if (x>180||x<-180) {
            throw new CustomException(ErrorType.INVALID_LOCATION);
        }
        double y = newTrashcanRequest.getLocation().getY();
        if (y>180||y<-180) {
            throw new CustomException(ErrorType.INVALID_LOCATION);
        }
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
            if (180<location.getX() || location.getX()<-180) {
                throw new CustomException(ErrorType.INVALID_LOCATION);
            }
            if (180<location.getY() || location.getY()<-180) {
                throw new CustomException(ErrorType.INVALID_LOCATION);
            }
            trashcan.setLocation(location);
        }
        else {
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
    }

    public Trashcan findOne(Long trashcanId) {
        // 컨트롤러에서 어차피 값들만 반환할거라 여기서는 엔티티 반환해도 됨
        return trashcanRepository.findById(trashcanId).get();
    }
}
