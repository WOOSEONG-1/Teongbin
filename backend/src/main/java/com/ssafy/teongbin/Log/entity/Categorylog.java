package com.ssafy.teongbin.Log.entity;

import com.ssafy.teongbin.Trash.entity.Trashcan;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Time;

@Entity
@Getter
@Table(name = "categorylog")
public class Categorylog {

    @Id @GeneratedValue
    private int id;

    private Time created_at;

    @ManyToOne
    @JoinColumn(name = "trashcan_id")
    private Trashcan trashcan;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
