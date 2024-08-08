package com.ssafy.teongbin.mail.entity;

import com.ssafy.teongbin.common.entity.BaseTime;
import com.ssafy.teongbin.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "code")
public class Code extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private int codenumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
