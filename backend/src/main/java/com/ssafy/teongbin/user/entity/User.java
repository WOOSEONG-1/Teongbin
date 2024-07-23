package com.ssafy.teongbin.user.entity;

import com.ssafy.teongbin.shortcut.entity.Shortcut;
import com.ssafy.teongbin.trash.entity.Trashcan;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    private String email;
    private String name;
    private String role;
    private String password;
    private Time created_at;

    @OneToMany(mappedBy = "user")
    private List<Trashcan> Trashcans = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Shortcut> Shortcuts = new ArrayList<>();

}
