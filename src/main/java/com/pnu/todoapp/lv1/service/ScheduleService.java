package com.pnu.todoapp.lv1.service;

import com.pnu.todoapp.lv1.dto.ResponseScheduleDto;
import com.pnu.todoapp.lv1.entity.Schedule;
import com.pnu.todoapp.lv1.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;
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

    public ResponseScheduleDto findById(Long id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정이 존재하지 않습니다.");
        }
        return new ResponseScheduleDto(schedule.get());
    }

    public List<ResponseScheduleDto> find(Optional<String> username, Optional<LocalDate> updatedAt) {
        List<Schedule> schedules;
        Date date = (updatedAt.isEmpty()) ? null : new Date(updatedAt.get().getYear(), updatedAt.get().getMonth(), updatedAt.get().getDayOfMonth());

        if (username.isEmpty() && updatedAt.isEmpty()) schedules = scheduleRepository.findAll();
        else if (updatedAt.isEmpty()) schedules = scheduleRepository.findByUsername(username.get());
        else if (username.isEmpty()) schedules = scheduleRepository.findByUpdatedAt(date);
        else schedules = scheduleRepository.findByUsernameAndUpdatedAt(username.get(), date);

        return schedules.stream().map(ResponseScheduleDto::new).toList();
    }


}
