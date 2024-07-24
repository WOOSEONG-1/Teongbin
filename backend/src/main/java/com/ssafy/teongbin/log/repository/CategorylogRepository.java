package com.ssafy.teongbin.log.repository;

import com.ssafy.teongbin.log.entity.Category;
import com.ssafy.teongbin.log.entity.Categorylog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorylogRepository extends JpaRepository<Categorylog, Long> {
}
