package com.pnu.todoapp.Lv5.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestCreateScheduleDto {
    private String content;
    private String password;
    private Long userId;
}
