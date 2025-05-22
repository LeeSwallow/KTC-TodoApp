package com.pnu.todoapp.Lv3.repository;

import com.pnu.todoapp.Lv3.dao.ResponseScheduleDao;
import com.pnu.todoapp.Lv3.dao.ScheduleDao;
import com.pnu.todoapp.Lv3.dao.UserDao;
import com.pnu.todoapp.Lv3.dto.ResponseScheduleDto;
import com.pnu.todoapp.Lv3.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ResponseScheduleRepository {
    private final UserDao userDao;
    private final ScheduleDao scheduleDao;
    private final ResponseScheduleDao responseScheduleDao;

    @Transactional
    public Optional<ResponseScheduleDto> save(String content, String password, Long userId) {
        Long createdId = scheduleDao.save(content, password, userId);
        if (createdId == null) return Optional.empty();
        return Optional.ofNullable(responseScheduleDao.findById(createdId));
    }

    public Optional<ResponseScheduleDto> findById(Long id) {
        return Optional.ofNullable(responseScheduleDao.findById(id));
    }

    public List<ResponseScheduleDto> findAll() {
        return responseScheduleDao.findAll().stream()
                .sorted(Comparator.comparing(ResponseScheduleDto::getUpdatedAt).reversed())
                .toList();
    }

    public List<ResponseScheduleDto> findByUsername(String username) {
        return responseScheduleDao.findByUsername(username).stream()
                .sorted(Comparator.comparing(ResponseScheduleDto::getUpdatedAt).reversed())
                .toList();
    }

    public List<ResponseScheduleDto> findByUpdatedAt(LocalDate updatedAt) {
        return responseScheduleDao.findByUpdatedAt(updatedAt);
    }

    public List<ResponseScheduleDto> findByUsernameAndUpdatedAt(String username, LocalDate updatedAt) {
        return responseScheduleDao.findByUsernameAndUpdatedAt(username, updatedAt);
    }

    @Transactional
    public Optional<ResponseScheduleDto> updateContentById(Long id, String content) {
        if (scheduleDao.updateContentById(id, content) > 0) {
            return Optional.ofNullable(responseScheduleDao.findById(id));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<ResponseScheduleDto> updateUsernameById(Long id, String username) {
        Schedule schedule = scheduleDao.findById(id);

        if (schedule == null) return Optional.empty();
        int rowCount = userDao.updateNameById(schedule.getId(), username);
        if (rowCount < 0) return Optional.empty();

        return Optional.ofNullable(responseScheduleDao.findById(id));
    }



}
