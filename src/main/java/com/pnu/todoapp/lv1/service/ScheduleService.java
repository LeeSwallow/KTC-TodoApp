package com.pnu.todoapp.lv1.service;

import com.pnu.todoapp.lv1.dto.ResponseScheduleDto;
import com.pnu.todoapp.lv1.entity.Schedule;
import com.pnu.todoapp.lv1.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ResponseScheduleDto save(String content, String password, String username) {
        Optional<Schedule> schedule = scheduleRepository.save(content, password, username);
        if (schedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "일정이 생성에 실패했습니다.");
        }
        return new ResponseScheduleDto(schedule.get());
    }

    public ResponseScheduleDto find(Long id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정이 존재하지 않습니다.");
        }
        return new ResponseScheduleDto(schedule.get());
    }

    public List<ResponseScheduleDto> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream().map(ResponseScheduleDto::new).toList();
    }


}
