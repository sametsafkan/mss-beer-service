package com.sametsafkan.mssbeerservice.web.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class MvcErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> handleConstraintErrors(ConstraintViolationException e){
        List<String> errors = e.getConstraintViolations().stream()
                                .map(ex -> ex.getPropertyPath() + " - " + ex.getMessage())
                                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, BAD_REQUEST);
    }
}
