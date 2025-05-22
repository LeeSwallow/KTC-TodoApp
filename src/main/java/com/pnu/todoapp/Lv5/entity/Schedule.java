package com.pnu.todoapp.Lv5.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;


@Table("schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private @Id Long id;

    private String content;
    private String password;

    private Long userId;

    private LocalDate createdAt;
    private LocalDate updatedAt;
}
