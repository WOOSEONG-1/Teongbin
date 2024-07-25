package com.ssafy.teongbin.log.service;

import com.ssafy.teongbin.log.entity.Restlog;
import com.ssafy.teongbin.log.repository.RestlogRepository;
import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.trash.repository.TrashcanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RestlogService {
    private final RestlogRepository restlogRepository;
    private final TrashcanRepository trashcanRepository;

    public Long join(String serialNumber, int restPercent) {
        Trashcan findTrashcan = trashcanRepository.findBySerialNumber(serialNumber);
        Restlog restlog = new Restlog();
        restlog.setTrashcan(findTrashcan);
        restlog.setRestPercent(restPercent);
        restlogRepository.save(restlog);
        return restlog.getId();
    }
}