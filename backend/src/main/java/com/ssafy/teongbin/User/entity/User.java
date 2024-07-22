package com.ssafy.teongbin.User.entity;

import com.ssafy.teongbin.Trash.entity.Trashcan;
import jakarta.persistence.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private int id;

    @OneToMany(mappedBy = "user")
    private List<Trashcan> Trashcans = new ArrayList<>();

    private String email;
    private String name;
    private String role;
    private String password;
    private Time created_at;


}
