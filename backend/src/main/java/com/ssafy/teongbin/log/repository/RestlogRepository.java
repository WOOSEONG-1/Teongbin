package com.ssafy.teongbin.log.repository;

import com.ssafy.teongbin.log.entity.Restlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestlogRepository extends JpaRepository<Restlog, Long> {
}
