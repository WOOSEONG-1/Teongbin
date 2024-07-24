package com.ssafy.teongbin.shortcut.entity;

import com.ssafy.teongbin.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Table(name = "shortcut")
@Getter @Setter
public class Shortcut {
// 나중에 빌더 추가 ! ( 보안 향상 )
// created_at 상속 받기!

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shortcut_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String nickname;
    private Point location;
    private int zoom_level;
    private LocalDateTime created_at;

}
