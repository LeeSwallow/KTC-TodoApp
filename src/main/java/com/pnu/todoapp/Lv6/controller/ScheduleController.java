package com.pnu.todoapp.Lv6.controller;

import com.pnu.todoapp.Lv6.dto.RequestCreateScheduleDto;
import com.pnu.todoapp.Lv6.dto.RequestDeleteScheduleDto;
import com.pnu.todoapp.Lv6.dto.RequestUpdateScheduleDto;
import com.pnu.todoapp.Lv6.dto.ResponseScheduleDto;
import com.pnu.todoapp.Lv6.service.ScheduleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ResponseScheduleDto> createSchedule(@Valid @RequestBody RequestCreateScheduleDto requestDto) {
        ResponseScheduleDto responseDto =  scheduleService.save(requestDto.getContent(), requestDto.getPassword(), requestDto.getUserId());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ResponseScheduleDto>> getManySchedules(
            @Positive(message = "page는 양의 정수값이여야 합니다") @RequestParam(defaultValue = "1") Integer page,
            @Positive(message = "size는 양의 정수값이여야 합니다") @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String updatedAt)
    {
        if (username == null && updatedAt == null) {
            List<ResponseScheduleDto> responseDto = scheduleService.findAll(page, size);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else if (updatedAt == null) {
            List<ResponseScheduleDto> responseDto = scheduleService.findByUsername(username,page, size);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else if (username == null) {
            List<ResponseScheduleDto> responseDto = scheduleService.findByUpdatedAt(LocalDate.parse(updatedAt),page, size);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            List<ResponseScheduleDto> responseDto = scheduleService.findByUsernameAndUpdatedAt(username, LocalDate.parse(updatedAt), page, size);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseScheduleDto> getSingleSchedule(
            @Positive(message = "id는 양의 정수값이여야 합니다") @PathVariable Long id) {
        ResponseScheduleDto responseDto = scheduleService.findById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseScheduleDto> updateSchedule(
            @Positive(message = "id는 양의 정수값이여야 합니다") @PathVariable Long id,
            @Valid @RequestBody RequestUpdateScheduleDto requestDto) {
        System.out.printf("Password: %s Content: %s Username: %s\n", requestDto.getPassword(), requestDto.getContent(), requestDto.getUsername());
        ResponseScheduleDto responseDto = scheduleService.update(id, requestDto.getPassword(), requestDto.getContent(),requestDto.getUsername());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @Positive(message = "id는 양의 정수값이여야 합니다")@PathVariable Long id,
            @Valid @RequestBody RequestDeleteScheduleDto requestDto) {
        scheduleService.delete(id, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
