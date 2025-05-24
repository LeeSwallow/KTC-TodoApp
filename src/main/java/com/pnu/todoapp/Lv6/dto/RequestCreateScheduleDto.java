package com.pnu.todoapp.Lv6.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestCreateScheduleDto {
    @NotEmpty(message = "내용을 작성해 주세요")
    @Size(min = 1, max= 200, message = "내용은 200자 이내로 작성해주세요")
    private String content;

    @NotEmpty(message = "비밀번호를 작성해 주세요")
    @Size(min = 1, max=50, message = "비밀번호는 50자 이내로 작성해주세요")
    private String password;

    @NotEmpty(message = "userId를 작성해 주세요")
    private Long userId;
}
