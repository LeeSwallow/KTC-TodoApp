package com.pnu.todoapp.Lv5.repository;

import com.pnu.todoapp.Lv5.dao.UserDao;
import com.pnu.todoapp.Lv5.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserDao userDao;

    @Transactional
    public Optional<User> save(String email, String name) {
        Long createdId = userDao.save(email, name);
        if (createdId == null) return Optional.empty();

        User createdUser = userDao.findById(createdId);
        return Optional.of(createdUser);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userDao.findById(id));
    }

    @Transactional
    public Optional<User> updateNameById(Long id, String name) {
        if (userDao.updateNameById(id, name) <= 0) return Optional.empty();
        return Optional.of(userDao.findById(id));
    }

    public Boolean deleteById(Long id) {
        return (userDao.deleteById(id) > 0);
    }
}
