package com.ssafy.teongbin.trash.repository;

import com.ssafy.teongbin.trash.dto.response.UserTrashcanRestDtoV2;
import com.ssafy.teongbin.trash.entity.Trashcan;
import com.ssafy.teongbin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TrashcanRepository extends JpaRepository<Trashcan, Long> {
    Trashcan findBySerialNumber(String serialNumber);

    List<Trashcan> findByUser(User user);

    @Query("SELECT new com.ssafy.teongbin.trash.dto.response.UserTrashcanRestDtoV2(t.id, " +
            "(SELECT r.restPercent FROM Restlog r WHERE r.trashcan.id = t.id ORDER BY r.id DESC LIMIT 1)) " +
            "FROM Trashcan t WHERE t.user = :user")
    List<UserTrashcanRestDtoV2> findTrashcanRestDtoV2ByUser(@Param("user") User user);
}
