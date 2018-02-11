package com.rpajaziti.bookshop.controller;

import com.rpajaziti.bookshop.custom.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.Set;

@RestControllerAdvice(annotations = {RestController.class})
public class RestErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseMessage> returnMessage(ConstraintViolationException exception) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            message.append(violation.getMessage().concat(";"));
        }

        return new ResponseEntity<>(new ResponseMessage().setMessage(message.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ResponseMessage> returnMessage(SQLException exception) {
        return new ResponseEntity<>(new ResponseMessage().setMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}

