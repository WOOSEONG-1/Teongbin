package com.ssafy.teongbin.shortcut.entity;

import com.ssafy.teongbin.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.awt.*;
import java.sql.Time;

@Entity
@Table(name = "shortcut")
@Getter
public class Shortcut {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shortcut_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String nickname;
    private Point location;
    private int zoom_level;
    private Time created_at;
}
