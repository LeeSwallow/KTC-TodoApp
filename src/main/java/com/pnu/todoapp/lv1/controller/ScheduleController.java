package com.pnu.todoapp.lv1.controller;

import com.pnu.todoapp.lv1.dao.ScheduleDao;
import com.pnu.todoapp.lv1.dto.RequestCreateScheduleDto;
import com.pnu.todoapp.lv1.dto.ResponseScheduleDto;
import com.pnu.todoapp.lv1.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ResponseScheduleDto>> get(@RequestParam String username, @RequestParam LocalDate updatedAt) {
        if (username == null && updatedAt == null) {
            List<ResponseScheduleDto> responseDtos = scheduleService.find();
            return new ResponseEntity<>(responseDtos, HttpStatus.OK);
        } else if (updatedAt == null) {
            List<ResponseScheduleDto> responseDtos = scheduleService.findAll();
        }
    }

}
