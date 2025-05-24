package com.pnu.todoapp.Lv6.repository;

import com.pnu.todoapp.Lv6.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagedScheduleRepository extends PagingAndSortingRepository<Schedule, Long> {
    Page<Schedule> findAll();
}
