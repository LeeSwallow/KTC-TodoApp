package com.pnu.todoapp.Lv6.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class RequestUpdateScheduleDto {
    @NotEmpty(message = "비밀번호를 작성해주세요")
    @Size(max=50, message="비밀번호는 50자 이내로 작성해주세요")
    private final String password;
    @Size(max= 100, message = "내용은 100자 이내로 입력해주세요")
    @Setter private String content;
    @Size(max= 50, message = "사용자 이름은 50자 이내로 입력해주세요")
    @Setter private String username;

    public RequestUpdateScheduleDto(String password) {
        this.password = password;
    }
}
