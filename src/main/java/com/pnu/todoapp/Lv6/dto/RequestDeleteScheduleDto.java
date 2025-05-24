package com.pnu.todoapp.Lv6.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestDeleteScheduleDto {
    @NotEmpty(message = "비밀번호를 작성해주세요")
    @Size(max=50, message="비밀번호는 50자 이내로 작성해주세요")
    private final String password;
}
