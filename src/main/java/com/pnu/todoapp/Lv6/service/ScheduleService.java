package com.pnu.todoapp.Lv6.service;

import com.pnu.todoapp.Lv6.dto.ResponseScheduleDto;
import com.pnu.todoapp.Lv6.entity.Schedule;
import com.pnu.todoapp.Lv6.repository.ResponseScheduleRepository;
import com.pnu.todoapp.Lv6.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ResponseScheduleRepository responseScheduleRepository;

    private void authenticatePassword(Long id, String password) throws ResponseStatusException {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다.");
        if (!schedule.get().getPassword().equals(password))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
    }

    public ResponseScheduleDto save(String content, String password, Long userId) {
        Optional<ResponseScheduleDto> response = responseScheduleRepository.save(content, password, userId);
        if (response.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "일정이 생성에 실패했습니다.");
        }
        return response.get();
    }

    public ResponseScheduleDto findById(Long id) {
        Optional<ResponseScheduleDto> schedule = responseScheduleRepository.findById(id);
        if (schedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정이 존재하지 않습니다.");
        }
        return schedule.get();
    }

    public List<ResponseScheduleDto> findAll(Integer page , Integer size) {
        return responseScheduleRepository.findAll((page-1) * size, size);
    }

    public List<ResponseScheduleDto> findByUsername(String username, Integer page , Integer size) {
        return responseScheduleRepository.findByUsername(username, (page-1) * size, size);
    }

    public List<ResponseScheduleDto> findByUpdatedAt(LocalDate updatedAt, Integer page , Integer size) {
        if (updatedAt == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "업데이트 날짜를 입력해주세요.");
        return responseScheduleRepository.findByUpdatedAt(updatedAt, (page-1) * size, size);
    }

    public List<ResponseScheduleDto> findByUsernameAndUpdatedAt(String username, LocalDate updatedAt, Integer page , Integer size) {
        if (username == null || updatedAt == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "사용자 이름과 업데이트 날짜를 모두 입력해주세요.");
        }
        return responseScheduleRepository.findByUsernameAndUpdatedAt(username, updatedAt, (page-1) * size, size);
    }

    @Transactional
    public ResponseScheduleDto update(Long id, String password, String content, String username) {
        authenticatePassword(id, password);
        Optional<ResponseScheduleDto> updatedSchedule = Optional.empty();

        if (username != null) {
            updatedSchedule = responseScheduleRepository.updateUsernameById(id, username);
            if (updatedSchedule.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정 내용 수정에 실패했습니다!");
        }
        if (content != null) {
            updatedSchedule = responseScheduleRepository.updateContentById(id, content);
            if (updatedSchedule.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자 이름 수정에 실패했습니다!");
        }
        if (updatedSchedule.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "수정할 내용이 존재하지 않습니다.");

        return updatedSchedule.get();
    }

    public void delete(Long id, String password) {
        authenticatePassword(id, password);
        if (!scheduleRepository.deleteUserById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정 삭제에 실패했습니다.");
        }
    }
}

