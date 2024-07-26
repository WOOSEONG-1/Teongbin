package com.ssafy.teongbin.shortcut.entity;

import com.ssafy.teongbin.common.entity.BaseTime;
import com.ssafy.teongbin.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Table(name = "shortcut")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shortcut extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shortcut_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String nickname;
    private Point location;
//    나중에 Point 재정의해야할 듯함...

    private int zoom_level;

}
