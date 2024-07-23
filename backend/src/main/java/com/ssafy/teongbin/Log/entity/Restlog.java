package com.ssafy.teongbin.Log.entity;

import com.ssafy.teongbin.Trash.entity.Trashcan;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Time;

@Entity
@Getter
@Table(name = "restlog")
public class Restlog {

    @Id @GeneratedValue
    private int id;

    private int rest_percent;
    private Time created_at;

    @ManyToOne
    @JoinColumn(name = "trashcan_id")
    private Trashcan trashcan;
}
