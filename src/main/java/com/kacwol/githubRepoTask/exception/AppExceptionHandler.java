package com.kacwol.githubRepoTask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(NotAcceptableException.class)
    public ResponseEntity<String> handleWrongApplicationException(NotAcceptableException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleHttpClientErrorException(UserNotFoundException e){
        return new ResponseEntity<>(new ExceptionMessage(
                HttpStatus.NOT_FOUND,
                e.getMessage()
        ), HttpStatus.NOT_FOUND
        );
    }
}
