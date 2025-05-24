package com.pnu.todoapp.Lv6.controller;

import com.pnu.todoapp.Lv6.dto.RequestCreateUserDto;
import com.pnu.todoapp.Lv6.dto.RequestUpdateUserDto;
import com.pnu.todoapp.Lv6.entity.User;
import com.pnu.todoapp.Lv6.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<User> getUserById(@Positive(message = "id는 양의 정수값이여야 합니다") @PathVariable Long id) {
        return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(
            @Valid @RequestBody RequestCreateUserDto requestDto
    ) {
        User created = userService.create(requestDto.getEmail(), requestDto.getName());
        return new ResponseEntity<>(created,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @Positive(message = "id는 양의 정수값이여야 합니다") @PathVariable Long id,
           @Valid @RequestBody RequestUpdateUserDto requestDto) {
        User updated = userService.update(id, requestDto.getName());
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(
            @Positive(message = "id는 양의 정수값이여야 합니다") @PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
