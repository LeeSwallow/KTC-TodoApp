package com.pnu.todoapp.lv1.dto;

import com.pnu.todoapp.lv1.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ResponseScheduleDto {
    private Long id;
    private String content;
    private Date createdAt;
    private Date updatedAt;

    public ResponseScheduleDto(Schedule schedule) {
        this.id = schedule.getId();
        this.content = schedule.getContent();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}
