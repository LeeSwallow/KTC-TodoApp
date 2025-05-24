package com.pnu.todoapp.Lv6.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestUpdateUserDto {
    @NotEmpty(message = "사용자 이름을 입력해주세요")
    @Size(max= 50, message = "사용자 이름은 50자 이내로 입력해주세요")
    private final String name;
}
