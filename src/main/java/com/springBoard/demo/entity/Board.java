package com.springBoard.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Date;

@Entity
@Data
@DynamicInsert
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private String password;

    private Date createdate;

    private int viewcount;

    public void addViewCount() {
        this.viewcount++;
    }
}
