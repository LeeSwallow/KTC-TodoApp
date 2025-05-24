package com.pnu.todoapp.Lv6.exception;

import com.pnu.todoapp.Lv6.dto.ExceptionResponseDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponseDto> handleConstraintViolationException(ConstraintViolationException e) {
        List<String> errors = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage).toList();

        return new ResponseEntity<>(new ExceptionResponseDto(errors.toString(), "validation"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionResponseDto> SQLExceptionException(SQLException e) {

        return new ResponseEntity<>(new ExceptionResponseDto(e.toString(), "sql"), HttpStatus.BAD_REQUEST);
    }
}
