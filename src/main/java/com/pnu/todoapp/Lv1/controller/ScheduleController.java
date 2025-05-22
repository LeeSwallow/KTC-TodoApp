package com.pnu.todoapp.Lv1.controller;

import com.pnu.todoapp.Lv1.dto.RequestCreateScheduleDto;
import com.pnu.todoapp.Lv1.dto.ResponseScheduleDto;
import com.pnu.todoapp.Lv1.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ResponseScheduleDto> create(@RequestBody RequestCreateScheduleDto requestDto) {
        ResponseScheduleDto responseDto =  scheduleService.save(requestDto.getContent(), requestDto.getPassword(), requestDto.getUsername());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ResponseScheduleDto>> getMany(@RequestParam(required = false) String username, @RequestParam(required = false) String updatedAt) {
        if (username == null && updatedAt == null) {
            List<ResponseScheduleDto> responseDto = scheduleService.findAll();
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else if (updatedAt == null) {
            List<ResponseScheduleDto> responseDto = scheduleService.findByUsername(username);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else if (username == null) {
            List<ResponseScheduleDto> responseDto = scheduleService.findByUpdatedAt(LocalDate.parse(updatedAt));
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            List<ResponseScheduleDto> responseDto = scheduleService.findByUsernameAndUpdatedAt(username, LocalDate.parse(updatedAt));
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseScheduleDto> getSingle(@PathVariable Long id) {
        ResponseScheduleDto responseDto = scheduleService.findById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
