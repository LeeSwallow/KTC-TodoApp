package com.pnu.todoapp.Lv4.dao;

import com.pnu.todoapp.Lv4.entity.Schedule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ScheduleDao {
    private final JdbcTemplate jdbcTemplate;


    private RowMapper<Schedule> scheduleRowMapper() {
        return ((rs, rowNum) -> {
            Schedule schedule = new Schedule();
            schedule.setId(rs.getLong("id"));
            schedule.setContent(rs.getString("content"));
            schedule.setPassword(rs.getString("password"));
            schedule.setUserId(rs.getLong("user_id"));
            schedule.setCreatedAt(rs.getDate("created_at").toLocalDate());
            schedule.setUpdatedAt(rs.getDate("updated_at").toLocalDate());
            return schedule;
        });
    }

    public Schedule findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM schedules WHERE id = ?", scheduleRowMapper(), id).stream().findFirst().orElse(null);
    }

    public int updateContentById(Long id, String content) {
        Date today = new Date(System.currentTimeMillis());
        return jdbcTemplate.update("UPDATE schedules SET content = ? , updated_at = ? WHERE id = ?", content, today, id);
    }

    public int deleteById(Long id) {
        int rowCount = jdbcTemplate.update("DELETE FROM schedules WHERE id = ?", id);
        System.out.println(rowCount);
        return rowCount;
    }

    public Long save(String content, String password, Long userId) {
        String query = "INSERT INTO schedules(content, password, user_id, created_at, updated_at) VALUES (?, ?, ?, ?, ? )";
        Date today = new Date(System.currentTimeMillis());

        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement pstmt =  con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, content);
            pstmt.setString(2, password);
            pstmt.setLong(3, userId);
            pstmt.setDate(4, today);
            pstmt.setDate(5, today);
            return pstmt;
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowCount = jdbcTemplate.update(preparedStatementCreator, keyHolder);
        if (rowCount > 0) return ((Number)keyHolder.getKeyList().get(0).get("GENERATED_KEY")).longValue();
        else return null;
    }

}
