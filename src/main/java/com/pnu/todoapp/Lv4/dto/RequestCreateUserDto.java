package com.pnu.todoapp.Lv4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestCreateUserDto {
    private final String email;
    private final String name;
}
