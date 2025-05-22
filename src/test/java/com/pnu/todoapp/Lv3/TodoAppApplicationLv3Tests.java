package com.pnu.todoapp.Lv3;

import com.pnu.todoapp.Lv3.dao.ScheduleDao;
import com.pnu.todoapp.Lv3.dao.UserDao;
import com.pnu.todoapp.Lv3.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(classes = TodoAppApplicationLv3.class)
class TodoAppApplicationLv3Tests {

    @Autowired
    UserDao userDao;
    @Autowired
    ScheduleDao scheduleDao;

    @Test
    void contextLoads() {
    }

    @Test
    void createUsersAndSchedules() {
        for (int i=1; i<=50; ++i) {
            String username = "user" + i;
            Long userId = userDao.save(username+"@test.com", username);
            System.out.println(username + " created!");
            if (userId != null) {
                for (int j=1; j<=50; ++j) {
                    String content = "My name is " + username + " " + j + "!"
                            + "I have to do work " + j;
                    String password = "password" + i;
                    scheduleDao.save(content, password, userId);
                }
            }
        }
    }

}
