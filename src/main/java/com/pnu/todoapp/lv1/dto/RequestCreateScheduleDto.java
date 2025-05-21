package com.pnu.todoapp.lv1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestCreateScheduleDto {
    private String content;
    private String password;
    private String username;
}
