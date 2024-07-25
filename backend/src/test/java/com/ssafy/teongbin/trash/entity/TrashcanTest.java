package com.ssafy.teongbin.trash.entity;

import com.ssafy.teongbin.trash.repository.TrashcanRepository;
import com.ssafy.teongbin.trash.service.TrashcanService;
import com.ssafy.teongbin.user.entity.User;
import com.ssafy.teongbin.user.repository.UserRepository;
import com.ssafy.teongbin.user.service.UserService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrashcanTest {

    @Autowired
    TrashcanService trashcanService;
    @Autowired
    TrashcanRepository trashcanRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 쓰레기통등록() {
        //given
        Trashcan trashcan = new Trashcan();
        trashcan.setNickname("trashcannicname1");
        Point point = new Point(10, 20);
        trashcan.setLocation(point);
        trashcan.setSerial_number("trashcanserial1");

        //when
        Long saveId = trashcanService.join(trashcan);

        //then
        Optional<Trashcan> foundtrashcan = trashcanRepository.findById(saveId);
        assertTrue(foundtrashcan.isPresent());
        assertEquals(trashcan.getNickname(), foundtrashcan.get().getNickname());

    }

    @Test
    public void 쓰레기통삭제() {

        trashcanService.deleteTrashcan(1L);

    }
}