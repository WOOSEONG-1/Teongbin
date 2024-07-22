package com.ssafy.teongbin.Trash.entity;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.User;

import java.awt.*;
import java.sql.Time;

@Entity
public class Trashcan {

    @Id @GeneratedValue
    @Column(name = "trashcan_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Character serial_number;
    private String nickname;
    private Point location;
    private Time created_at;
}
