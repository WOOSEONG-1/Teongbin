package com.ssafy.teongbin.user.entity;

import com.ssafy.teongbin.common.entity.BaseTime;
import com.ssafy.teongbin.shortcut.entity.Shortcut;
import com.ssafy.teongbin.trash.entity.Trashcan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String name;
    private String role;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Trashcan> Trashcans = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Shortcut> Shortcuts = new ArrayList<>();

}
