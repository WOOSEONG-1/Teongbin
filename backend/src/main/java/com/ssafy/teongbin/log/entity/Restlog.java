package com.ssafy.teongbin.log.entity;

import com.ssafy.teongbin.common.entity.BaseTime;
import com.ssafy.teongbin.trash.entity.Trashcan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "restlog")
public class Restlog extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restlog_id")
    private Long id;

    private int restPercent;

    @ManyToOne
    @JoinColumn(name = "trashcan_id")
    private Trashcan trashcan;
}
