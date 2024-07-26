package com.ssafy.teongbin.shortcut.repository;

import com.ssafy.teongbin.shortcut.entity.Shortcut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShortcutRepository extends JpaRepository<Shortcut, Long> {
    public List<Shortcut> findByUserId(Integer UserId);
}
