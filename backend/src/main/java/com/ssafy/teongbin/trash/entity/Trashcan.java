package com.ssafy.teongbin.trash.entity;

import com.ssafy.teongbin.log.entity.Categorylog;
import com.ssafy.teongbin.log.entity.Restlog;
import com.ssafy.teongbin.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "trashcan")
public class Trashcan {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trashcan_id")
    private int id;

    private Character serial_number;
    private String nickname;
    private Point location;
    private Time created_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "trashcan")
    private List<Categorylog> categorylogs = new ArrayList<>();

    @OneToMany(mappedBy = "trashcan")
    private List<Restlog> restlogs = new ArrayList<>();
}
