package com.pnu.todoapp.Lv1.dao;

import com.pnu.todoapp.Lv1.entity.Schedule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.List;

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
            schedule.setUsername(rs.getString("username"));
            schedule.setCreatedAt(rs.getDate("created_at").toLocalDate());
            schedule.setUpdatedAt(rs.getDate("updated_at").toLocalDate());
            return schedule;
        });
    }

    public Schedule findById(Long id) {
        List<Schedule> schedules = jdbcTemplate.query("SELECT * FROM schedule WHERE id = ?", scheduleRowMapper(), id);
        return schedules.isEmpty() ? null : schedules.get(0);
    }

    public List<Schedule> findAll() {
        return jdbcTemplate.query("SELECT * FROM schedule", scheduleRowMapper());
    }

    public int updateContentById(Long id, String content) {
        java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
        return jdbcTemplate.update("UPDATE schedule SET content = ? , updated_at = ? WHERE id = ?", content, today, id);
    }

    public int updateUsernameById(Long id, String username) {
        Date today = new Date(System.currentTimeMillis());
        return jdbcTemplate.update("UPDATE schedule SET username = ? , updated_at = ? WHERE id = ?", username, today, id);
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM schedule WHERE id = ?", id);
    }

    public Long save(String content, String password, String username) {
        String query = "INSERT INTO schedule(content, password, username, created_at, updated_at) VALUES (?, ?, ?, ?, ? )";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        java.sql.Date today = new java.sql.Date(System.currentTimeMillis());

        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement pstmt =  con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, content);
            pstmt.setString(2, password);
            pstmt.setString(3, username);
            pstmt.setDate(4, today);
            pstmt.setDate(5, today);
            return pstmt;
        };

        int rowCount = jdbcTemplate.update(preparedStatementCreator, keyHolder);
        if (rowCount > 0) return ((Number)keyHolder.getKeyList().get(0).get("id")).longValue();
        else return -1L;
    }

}
