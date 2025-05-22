package com.pnu.todoapp.Lv3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class RequestUpdateScheduleDto {
    private final String password;
    @Setter private String content;
    @Setter private String username;

    public RequestUpdateScheduleDto(String password) {
        this.password = password;
    }
}
