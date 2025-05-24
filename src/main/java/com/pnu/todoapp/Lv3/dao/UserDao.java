package com.pnu.todoapp.Lv3.dao;

import com.pnu.todoapp.Lv3.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(
            rs.getLong("id"),
            rs.getString("email"),
            rs.getString("name"),
            rs.getDate("created_at").toLocalDate(),
            rs.getDate("updated_at").toLocalDate()
        );
    }

    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", userRowMapper());
    }

    public User findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id = ?", userRowMapper(), id)
                .stream().findFirst().orElse(null);
    }

    public int updateNameById(Long id, String name) {
        Date today = new Date(System.currentTimeMillis());
        return jdbcTemplate.update("UPDATE users SET name = ? WHERE id = ?", name, id);
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }

    public Long save(String email, String name) {
        Date today = new Date(System.currentTimeMillis());
        PreparedStatementCreator psc= new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement= con.prepareStatement(
                        "INSERT INTO users(email, name, created_at, updated_at) VALUES(?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, name);
                preparedStatement.setDate(3, today);
                preparedStatement.setDate(4, today);
                return preparedStatement;
            }
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowCount = jdbcTemplate.update(psc, keyHolder);
        if (rowCount <= 0) return null;
        return ((Number)keyHolder.getKeyList().get(0).get("GENERATED_KEY")).longValue();
    }


}
