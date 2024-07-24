package com.ssafy.teongbin.log.repository;

import com.ssafy.teongbin.log.entity.Restlog;
import com.ssafy.teongbin.log.service.RestlogRepository;
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

    public Long join(Restlog restlog) {
        restlogRepository.save(restlog);
        return restlog.getId();
    }
}