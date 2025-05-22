package com.pnu.todoapp.Lv1.dto;

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
