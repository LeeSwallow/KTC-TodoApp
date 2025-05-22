package com.pnu.todoapp.Lv5.dto;

import com.pnu.todoapp.Lv2.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ResponseScheduleDto {
    private Long id;
    private String content;
    private String username;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public ResponseScheduleDto(Schedule schedule) {
        this.id = schedule.getId();
        this.content = schedule.getContent();
        this.username = schedule.getUsername();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}
