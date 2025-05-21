package com.pnu.todoapp.lv1.controller;

import com.pnu.todoapp.lv1.dao.ScheduleDao;
import com.pnu.todoapp.lv1.dto.RequestCreateScheduleDto;
import com.pnu.todoapp.lv1.dto.ResponseScheduleDto;
import com.pnu.todoapp.lv1.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ResponseScheduleDto> create(@ModelAttribute RequestCreateScheduleDto requestDto) {
        ResponseScheduleDto responseDto =  scheduleService.save(requestDto.getContent(), requestDto.getPassword(), requestDto.getUsername());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

}
