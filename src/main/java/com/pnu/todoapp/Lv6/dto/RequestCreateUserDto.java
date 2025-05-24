package com.pnu.todoapp.Lv6.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestCreateUserDto {
    @NotEmpty(message = "이메일을 작성해주세요")
    @Email(message = "이메일 양식으로 작성해 주세요")
    @Size(min=1, max=100, message = "이메일은 100자 이내로 작성해주세요")
    private final String email;
    @NotEmpty(message = "이름을 작성해주세요")
    @Size(min=1, max=100, message = "이름은 100자 이내로 작성해주세요")
    private final String name;
}
