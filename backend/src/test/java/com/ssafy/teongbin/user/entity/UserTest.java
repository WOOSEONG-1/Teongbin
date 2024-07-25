package com.ssafy.teongbin.user.entity;

import com.ssafy.teongbin.user.repository.UserRepository;
import com.ssafy.teongbin.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    public void 회원가입() {
        //given
        User user = new User();
//        user.setEmail("emailtest");

        //when
//        Long saveId = userService.join(user);

        //then
//        Optional<User> foundUser = userRepository.findById(saveId);
//        assertTrue(foundUser.isPresent());
//        assertEquals(user.getEmail(), foundUser.get().getEmail());
    }

}