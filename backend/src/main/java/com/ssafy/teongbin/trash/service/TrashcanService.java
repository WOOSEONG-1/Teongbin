package com.ssafy.teongbin.trash.service;

import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.trash.repository.TrashcanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrashcanService {

    private final TrashcanRepository trashcanRepository;

    @Transactional
    public Long join(Trashcan trashcan) {
        trashcan.setCreated_at(LocalDateTime.now());
        trashcanRepository.save(trashcan);
        return trashcan.getId();
    }

    @Transactional
    public void deleteTrashcan(Long trashcanId) {
        //에러 구현
        //사용자 인증 구현
        trashcanRepository.deleteById(trashcanId);
    }
}
