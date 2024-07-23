package com.ssafy.teongbin.shortcut.repository;

import com.ssafy.teongbin.shortcut.entity.Shortcut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortcutRepository extends JpaRepository<Shortcut, Long> {
//    public Optional<Shortcut> findByNickName(String nickname);
    // 리스트는 Optional로 안받아도 됨.
//    Optinal<Shortcut> os ;
//    if(os.isPresent()){
//
//    }
}
