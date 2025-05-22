package com.pnu.todoapp.Lv5.repository;

import com.pnu.todoapp.Lv5.dao.ScheduleDao;
import com.pnu.todoapp.Lv5.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScheduleRepository {
    final ScheduleDao scheduleDao;

    public Optional<Schedule> findById(Long id) {
        return Optional.ofNullable(scheduleDao.findById(id));
    }
    public boolean deleteUserById(Long id) {
        return (scheduleDao.deleteById(id) > 0);
    }
}
