package com.ssafy.teongbin.trash.service;

import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.trash.repository.TrashcanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrashcanService {

    private final TrashcanRepository trashcanRepository;

    @Transactional
    public Long join(Trashcan trashcan) {
        trashcanRepository.save(trashcan);
        return trashcan.getId();
    }

    @Transactional
    public void deleteTrashcan(Long trashcanId) {
        Trashcan trashcan = trashcanRepository.findById(trashcanId)
                        .orElseThrow(() -> new IllegalArgumentException("쓰레기통이 존재하지 않습니다."));
        trashcanRepository.deleteById(trashcanId);
    }

    @Transactional
    public void update(Long id, String nickname, Point location) {
        Trashcan trashcan = trashcanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("쓰레기통이 존재하지 않습니다."));
        trashcan.setNickname(nickname);
        trashcan.setLocation(location);
    }

    public Trashcan findOne(Long trashcanId) {
        return trashcanRepository.findById(trashcanId).get();
    }
}
