package com.pnu.todoapp.lv1.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
public class Schedule {
    Long id;
    String content;
    String username;
    String password;
    Date createdAt;
    Date updatedAt;
}
