package com.ssafy.teongbin.user.repository;


import com.ssafy.teongbin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
