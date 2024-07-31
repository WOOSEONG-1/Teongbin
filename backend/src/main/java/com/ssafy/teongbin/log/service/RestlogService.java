package com.ssafy.teongbin.log.service;

import com.ssafy.teongbin.common.exception.CustomException;
import com.ssafy.teongbin.common.exception.ErrorType;
import com.ssafy.teongbin.log.dto.request.RestlogRequest;
import com.ssafy.teongbin.log.entity.Restlog;
import com.ssafy.teongbin.log.repository.RestlogRepository;
import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.trash.repository.TrashcanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RestlogService {
    private final RestlogRepository restlogRepository;
    private final TrashcanRepository trashcanRepository;

    public Long join(RestlogRequest request) {
        String sn = request.getSerialNumber();
        if (sn == null || sn.isEmpty()) {
            throw new CustomException(ErrorType.NOT_FOUND_SERIAL);
        }
        Trashcan findTrashcan = trashcanRepository.findBySerialNumber(sn);
        if (findTrashcan == null) {
            throw new CustomException(ErrorType.NOT_FOUND_TRASHCAN);
        }
        Restlog restlog = new Restlog();
        restlog.setTrashcan(findTrashcan);
        Integer rp = request.getRestPercent();
        if (rp==null) {
            throw new CustomException(ErrorType.NOT_FOUND_REST);
        } else if (rp<0 || rp>100) {
            throw new CustomException(ErrorType.INVALID_REST);
        }
        restlog.setRestPercent(request.getRestPercent());
        restlogRepository.save(restlog);
        Long restlogId = restlog.getId();
        if (restlogId==null) {
            throw new CustomException(ErrorType.FAILED_TO_RESTLOG);
        }
        return restlogId;
    }
}