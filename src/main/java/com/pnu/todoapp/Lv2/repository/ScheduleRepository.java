package com.pnu.todoapp.Lv2.repository;

import com.pnu.todoapp.Lv2.dao.ScheduleDao;
import com.pnu.todoapp.Lv2.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScheduleRepository {
    private final ScheduleDao scheduleDao;

    @Transactional
    public Optional<Schedule> save(String content, String password, String username) {
        Long id = scheduleDao.save(content, password, username);
        if (id < 0) return Optional.empty();
        return Optional.ofNullable(scheduleDao.findById(id));
    }

    public Optional<Schedule> findById(Long id) {
        return Optional.ofNullable(scheduleDao.findById(id));
    }

    public List<Schedule> findAll() {
        return scheduleDao.findAll().stream().sorted(Comparator.comparing(Schedule::getUpdatedAt).reversed())
                .toList();
    }

    public List<Schedule> findByUsername(String username) {
        return scheduleDao.findAll().stream()
                .filter(schedule -> schedule.getUsername().equals(username))
                .sorted(Comparator.comparing(Schedule::getUpdatedAt).reversed())
                .toList();
    }

    public List<Schedule> findByUpdatedAt(LocalDate updatedAt) {
        return scheduleDao.findAll().stream()
                .filter(schedule -> schedule.getUpdatedAt().equals(updatedAt))
                .toList(); //  update date를 조회하므로 sort 해줄 필요 없음
    }

    public List<Schedule> findByUsernameAndUpdatedAt(String username, LocalDate updatedAt) {
        return scheduleDao.findAll().stream()
                .filter(schedule ->
                    schedule.getUsername().equals(username) && schedule.getUpdatedAt().equals(updatedAt)).toList();
    }

    @Transactional
    public Optional<Schedule> updateContentById(Long id, String content) {
        if (scheduleDao.updateContentById(id, content) > 0) {
            return Optional.ofNullable(scheduleDao.findById(id));
        }
        return Optional.empty();
    }


    @Transactional
    public Optional<Schedule> updateUsernameById(Long id, String username) {
        if (scheduleDao.updateUsernameById(id, username) > 0) {
            return Optional.ofNullable(scheduleDao.findById(id));
        }
        return Optional.empty();
    }

    public boolean deleteUserById(Long id) {
        return (scheduleDao.deleteById(id) > 0);
    }
}
