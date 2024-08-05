package com.ssafy.teongbin.trash.service;

import com.ssafy.teongbin.common.exception.CustomException;
import com.ssafy.teongbin.common.exception.ErrorType;
import com.ssafy.teongbin.common.jwt.PrincipalDetails;
import com.ssafy.teongbin.log.entity.Catlog;
import com.ssafy.teongbin.trash.dto.response.*;
import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.trash.repository.TrashcanRepository;
import com.ssafy.teongbin.user.entity.User;
import com.ssafy.teongbin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserTrashcanService {

    private final UserRepository userRepository;
    private final TrashcanRepository trashcanRepository;

    public List<UserTrashcanRestDto> userTrashcanRest (PrincipalDetails userIn) {
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
            List<UserTrashcanRestDto> utd = lt.stream()
                    .map(t -> {
                        try {
                            return new UserTrashcanRestDto(t);
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

    public List<UserTrashcanRestDtoV2> userTrashcanRestV2 (PrincipalDetails userIn) {
        String username = userIn.getUsername();
        if (username == null || username.trim().isEmpty()) {
            throw new CustomException(ErrorType.NOT_FOUND_USERNAME);
        }
        Optional<User> ou = userRepository.findByEmail(userIn.getUsername());
        if (ou.isPresent()) {
            List<UserTrashcanRestDtoV2> utd = trashcanRepository.findTrashcanRestDtoV2ByUser(ou.get());
            if (utd.isEmpty()) {
                throw new CustomException(ErrorType.NOT_FOUND_TRASHCAN);
            }
            return utd;
        }
        else {
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
    }

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

    public List<UserLogDto.RestDto> userRestlog (PrincipalDetails userIn) {
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
            List<UserLogDto.RestDto> uld = lt.stream()
                    .flatMap(trashcan -> trashcan.getRestlogs().stream())
                    .map(UserLogDto.RestDto::new)
                    .sorted(Comparator.comparing(UserLogDto.RestDto::getCreatedAt).reversed())
                    .collect(Collectors.toList());
            return uld;
        }
        else {
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
    }

    public List<UserLogDto.RestDto> userRestlogV2 (PrincipalDetails userIn) {
        String username = userIn.getUsername();
        if (username == null || username.trim().isEmpty()) {
            throw new CustomException(ErrorType.NOT_FOUND_USERNAME);
        }
        Optional<User> ou = userRepository.findByEmail(userIn.getUsername());
        if (ou.isPresent()) {
            List<Trashcan> lt = trashcanRepository.findByUser(ou.get());
            if (lt.isEmpty()) {
                throw new CustomException(ErrorType.NOT_FOUND_TRASHCAN);
            }
            List<Long> trashcanIds = lt.stream()
                    .map(Trashcan::getId)
                    .collect(Collectors.toList());
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime nowMinus3 = now.minusDays(3);
            List<UserLogDto.RestDto> uld = trashcanRepository.findRestDto(trashcanIds, nowMinus3);
            return uld;
        } else {
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
    }

    public List<TrashcanCatlogDto> trashcanCatlog(PrincipalDetails userIn, Long trashcanId) {
        Optional<User> ou = userRepository.findByEmail(userIn.getUsername());
        if (ou.isPresent()) {
            Optional<Trashcan> ot = trashcanRepository.findById(trashcanId);
            if (ot.isPresent()) {

                TrashcanCatlogDto tcd1 = new TrashcanCatlogDto();
                tcd1.setCategoryId(1L);
                tcd1.setCount(0);

                TrashcanCatlogDto tcd2 = new TrashcanCatlogDto();
                tcd2.setCategoryId(2L);
                tcd2.setCount(0);

                TrashcanCatlogDto tcd3 = new TrashcanCatlogDto();
                tcd3.setCategoryId(3L);
                tcd3.setCount(0);

                List<Catlog> catlogs = ot.get().getCatlogs();

                for (Catlog catlog : catlogs) {
                    Long categoryId = catlog.getCategory().getId();
                    if (categoryId==1L) {
                        tcd1.setCount(tcd1.getCount()+1);
                    } else if (categoryId==2L) {
                        tcd2.setCount(tcd2.getCount()+1);
                    } else if (categoryId==3L) {
                        tcd3.setCount(tcd3.getCount()+1);
                    } else {
                        throw new CustomException(ErrorType.NOT_FOUND_CATEGORYID);
                    }
                }
                Map<Long, TrashcanCatlogDto> categoryMap = new HashMap<>();
                categoryMap.put(1L, tcd1);
                categoryMap.put(2L, tcd2);
                categoryMap.put(3L, tcd3);

                return new ArrayList<>(categoryMap.values());
            } else {
                throw new CustomException(ErrorType.NOT_FOUND_TRASHCAN);
            }
        } else {
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
    }
}
