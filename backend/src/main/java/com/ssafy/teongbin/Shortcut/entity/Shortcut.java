package com.ssafy.teongbin.Shortcut.entity;

import com.ssafy.teongbin.User.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.sql.Time;

@Entity
@Table(name = "shortcut")
@Getter
public class Shortcut {

    @Id @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String nickname;
    private Point location;
    private int zoom_level;
    private Time created_at;
}
