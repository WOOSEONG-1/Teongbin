package com.ssafy.teongbin.trash.repository;

import com.ssafy.teongbin.trash.entity.Trashcan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrashcanRepository extends JpaRepository<Trashcan, Long> {
    Trashcan findBySerialNumber(String serialNumber);
}
