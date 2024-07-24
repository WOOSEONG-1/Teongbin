package com.ssafy.teongbin.trash.service;

import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.trash.repository.TrashcanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
