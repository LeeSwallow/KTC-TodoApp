package com.pnu.todoapp.Lv6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponseDto {
    private String message;
    private String type;
}
