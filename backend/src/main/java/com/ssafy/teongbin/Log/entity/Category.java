package com.ssafy.teongbin.Log.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "category")
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private int id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Categorylog> categorylogs = new ArrayList<>();
}
