package com.pnu.todoapp.Lv1.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
public class Schedule {
    Long id;
    String content;
    String username;
    String password;
    LocalDate createdAt;
    LocalDate updatedAt;
}
