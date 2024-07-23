package com.ssafy.teongbin.log.entity;

import com.ssafy.teongbin.trash.entity.Trashcan;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Time;

@Entity
@Getter
@Table(name = "categorylog")
public class Categorylog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorylog_id")
    private int id;

    private Time created_at;

    @ManyToOne
    @JoinColumn(name = "trashcan_id")
    private Trashcan trashcan;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
