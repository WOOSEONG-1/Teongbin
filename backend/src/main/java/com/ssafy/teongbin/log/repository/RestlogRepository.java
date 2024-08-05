package com.ssafy.teongbin.log.repository;

import com.ssafy.teongbin.log.entity.Restlog;
import com.ssafy.teongbin.trash.entity.Trashcan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface RestlogRepository extends JpaRepository<Restlog, Long> {
    List<Restlog> findByTrashcanId(long l);
}
