package com.pnu.todoapp.Lv5.dao;

import com.pnu.todoapp.Lv5.dto.ResponseScheduleDto;
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
            response.setUsername(rs.getString("name"));
            response.setContent(rs.getString("content"));
            response.setCreatedAt(rs.getDate("created_at").toLocalDate());
            response.setUpdatedAt(rs.getDate("updated_at").toLocalDate());
            return response;
        };
    }

    public List<ResponseScheduleDto> findAll(Integer offset, Integer limit) {
        String sql = "SELECT sch.id, u.name, sch.content, sch.created_at, sch.updated_at ";
        sql += "from schedules sch JOIN users u ON sch.user_id = u.id ";
        sql += "order by sch.updated_at desc, sch.id  offset ? limit ?";
        return jdbcTemplate.query(sql, responseScheduleRowMapper(), offset, limit);
    }

    public ResponseScheduleDto findById(Long id) {
        String sql = "SELECT sch.id, u.name, sch.content, sch.created_at, sch.updated_at from schedules sch JOIN users u ON sch.user_id = u.id ";
        sql += " WHERE sch.id = ? ";
        return jdbcTemplate.query(sql, responseScheduleRowMapper(), id).stream().findFirst().orElse(null);
    }

    public List<ResponseScheduleDto> findByUsername(String username, Integer offset, Integer limit) {
        String sql = "SELECT sch.id, u.name, sch.content, sch.created_at, sch.updated_at from schedules sch JOIN users u ON sch.user_id = u.id";
        sql += " WHERE u.name = ?";
        sql += "order by sch.updated_at desc, sch.id  offset ? limit ?";
        return jdbcTemplate.query(sql, responseScheduleRowMapper(), username, offset, limit);
    }

    public List<ResponseScheduleDto> findByUpdatedAt(LocalDate updatedAt, Integer offset, Integer limit) {
        String sql = "SELECT sch.id, u.name, sch.content, sch.created_at, sch.updated_at from schedules sch JOIN users u ON sch.user_id = u.id";
        sql += " WHERE sch.updated_at = ?";
        sql += "order by sch.updated_at desc, sch.id  offset ? limit ?";
        return jdbcTemplate.query(sql, responseScheduleRowMapper(), Date.valueOf(updatedAt), offset, limit);
    }

    public List<ResponseScheduleDto> findByUsernameAndUpdatedAt(String username, LocalDate updatedAt, Integer offset, Integer limit) {
        String sql = "SELECT sch.id, u.name, sch.content, sch.created_at, sch.updated_at from schedules sch JOIN users u ON sch.user_id = u.id";
        sql += " WHERE u.name = ?";
        sql += " AND sch.updated_at = ?";
        sql += "order by sch.updated_at desc, sch.id  offset ? limit ?";
        return jdbcTemplate.query(sql, responseScheduleRowMapper(), username, updatedAt, offset, limit);
    }
}
