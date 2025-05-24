package com.pnu.todoapp.Lv6.service;

import com.pnu.todoapp.Lv6.entity.User;
import com.pnu.todoapp.Lv6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User create(String email, String name) {
        Optional<User> created = userRepository.save(email, name);
        if (created.isEmpty())  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "사용자 생성에 실패했습니다.");
        return created.get();
    }

    public User get(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 id의 사용자를 찾을 수 없습니다.");
        return user.get();
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User update(Long id, String name) {
        Optional<User> updated = userRepository.updateNameById(id, name);
        if (updated.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 id의 사용자를 찾을 수 없습니다.");
        return updated.get();
    }

    public void delete(Long id) {
        if (!userRepository.deleteById(id)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제를 실패했습니다.");
    }

}
