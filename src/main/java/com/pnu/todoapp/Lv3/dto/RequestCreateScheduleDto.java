package com.pnu.todoapp.Lv3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestCreateScheduleDto {
    private String content;
    private String password;
    private Long userId;
}
