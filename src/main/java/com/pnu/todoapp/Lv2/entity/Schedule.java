package com.pnu.todoapp.Lv2.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


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
