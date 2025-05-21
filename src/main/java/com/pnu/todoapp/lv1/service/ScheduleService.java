package com.pnu.todoapp.lv1.service;

import com.pnu.todoapp.lv1.dto.ResponseScheduleDto;
import com.pnu.todoapp.lv1.entity.Schedule;
import com.pnu.todoapp.lv1.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public List<ResponseScheduleDto> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream().map(ResponseScheduleDto::new).toList();
    }

    public List<ResponseScheduleDto> findByUsername(String username) {
        List<Schedule> schedules = scheduleRepository.findByUsername(username);
        return schedules.stream().map(ResponseScheduleDto::new).toList();
    }

    public List<ResponseScheduleDto> findByUpdatedAt(LocalDate updatedAt) {
        if (updatedAt == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "업데이트 날짜를 입력해주세요.");
        Date date = new Date(updatedAt.toEpochDay());
        List<Schedule> schedules = scheduleRepository.findByUpdatedAt(date);
        return schedules.stream().map(ResponseScheduleDto::new).toList();
    }

    public List<ResponseScheduleDto> findByUsernameAndUpdatedAt(String username, LocalDate updatedAt) {
        if (username == null || updatedAt == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "사용자 이름과 업데이트 날짜를 모두 입력해주세요.");
        }
        Date date = new Date(updatedAt.toEpochDay());
        List<Schedule> schedules = scheduleRepository.findByUsernameAndUpdatedAt(username, date);
        return schedules.stream().map(ResponseScheduleDto::new).toList();
    }

    @Transactional
    public ResponseScheduleDto update(Long id, String password, String content, String username) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정이 존재하지 않습니다.");
        }

        Schedule existingSchedule = schedule.get();

        if (!existingSchedule.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        if (content != null) {
            existingSchedule.setContent(content);
        }
        if (username != null) {
            existingSchedule.setUsername(username);
        }
        return new ResponseScheduleDto(schedule.get());
    }

    public void delete(Long id, String password) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정이 존재하지 않습니다.");
        }
        Schedule existingSchedule = schedule.get();

        if (!existingSchedule.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        if (scheduleRepository.deleteUserById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정 삭제에 실패했습니다.");
        }
    }
}

