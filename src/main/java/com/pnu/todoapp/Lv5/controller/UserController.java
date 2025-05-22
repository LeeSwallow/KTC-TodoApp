package com.pnu.todoapp.Lv5.controller;

import com.pnu.todoapp.Lv5.dto.RequestCreateUserDto;
import com.pnu.todoapp.Lv5.dto.RequestUpdateUserDto;
import com.pnu.todoapp.Lv5.entity.User;
import com.pnu.todoapp.Lv5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody RequestCreateUserDto requestDto) {
        User created = userService.create(requestDto.getEmail(), requestDto.getName());
        return new ResponseEntity<>(created,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody RequestUpdateUserDto requestDto) {
        User updated = userService.update(id, requestDto.getName());
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
