package com.pnu.todoapp.Lv2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestCreateScheduleDto {
    private String content;
    private String password;
    private String username;
}
