package com.ssafy.teongbin.trash.repository;

import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrashcanRepository extends JpaRepository<Trashcan, Long> {
    Trashcan findBySerialNumber(String serialNumber);

    List<Trashcan> findByUser(User user);
}
