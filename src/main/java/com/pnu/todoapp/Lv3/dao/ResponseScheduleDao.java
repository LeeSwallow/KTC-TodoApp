package com.pnu.todoapp.Lv3.dao;

import com.pnu.todoapp.Lv3.dto.ResponseScheduleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResponseScheduleDao {
    private final JdbcTemplate jdbcTemplate;
    private RowMapper<ResponseScheduleDto> responseScheduleRowMapper() {
        return (rs, rowNum) -> {
            ResponseScheduleDto response = new ResponseScheduleDto();
            response.setId(rs.getLong("id"));
            response.setUsername(rs.getString("username"));
            response.setContent(rs.getString("content"));
            response.setCreatedAt(response.getCreatedAt());
            response.setUpdatedAt(response.getUpdatedAt());
            return response;
        };
    }

    public List<ResponseScheduleDto> findAll() {
        String sql = "SELECT sch.id, u.name, sch.content, sch.created_at, sch.updated_at from schedules sch JOIN users u ON sch.user_id = u.id";
        return jdbcTemplate.query(sql, responseScheduleRowMapper());
    }

    public ResponseScheduleDto findById(Long id) {
        String sql = "SELECT sch.id, u.name, sch.content, sch.created_at, sch.updated_at from schedules sch JOIN users u ON sch.user_id = u.id";
        sql += " WHERE sch.id = ?";
        return jdbcTemplate.query(sql, responseScheduleRowMapper(), id).stream().findFirst().orElse(null);
    }

    public List<ResponseScheduleDto> findByUsername(String username) {
        String sql = "SELECT sch.id, u.name, sch.content, sch.created_at, sch.updated_at from schedules sch JOIN users u ON sch.user_id = u.id";
        sql += " WHERE u.name = ?";
        return jdbcTemplate.query(sql, responseScheduleRowMapper(), username);
    }

    public List<ResponseScheduleDto> findByUpdatedAt(LocalDate updatedAt) {
        String sql = "SELECT sch.id, u.name, sch.content, sch.created_at, sch.updated_at from schedules sch JOIN users u ON sch.user_id = u.id";
        sql += " WHERE sch.updated_at = ?";
        return jdbcTemplate.query(sql, responseScheduleRowMapper(), Date.valueOf(updatedAt));
    }

    public List<ResponseScheduleDto> findByUsernameAndUpdatedAt(String username, LocalDate updatedAt) {
        String sql = "SELECT sch.id, u.name, sch.content, sch.created_at, sch.updated_at from schedules sch JOIN users u ON sch.user_id = u.id";
        sql += " WHERE u.name = ?";
        sql += " AND sch.updated_at = ?";
        return jdbcTemplate.query(sql, responseScheduleRowMapper(), username, updatedAt);
    }
}
