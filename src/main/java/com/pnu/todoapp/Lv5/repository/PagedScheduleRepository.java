package com.pnu.todoapp.Lv5.repository;

import com.pnu.todoapp.Lv5.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagedScheduleRepository extends PagingAndSortingRepository<Schedule, Long> {
    Page<Schedule> findAll();
}
