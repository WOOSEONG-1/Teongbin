package com.ssafy.teongbin.shortcut.repository;

import com.ssafy.teongbin.shortcut.entity.Shortcut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShortcutRepository extends JpaRepository<Shortcut, Long> {
    public List<Shortcut> findByUserId(Long UserId);
    // Optional(T) 형태로 반환된다...
    //    public Optional<Shortcut> findByNickName(String nickname);
    // 리스트는 Optional로 안받아도 됨.
//    Optinal<Shortcut> os ;
//    if(os.isPresent()){
//
//    }
}
