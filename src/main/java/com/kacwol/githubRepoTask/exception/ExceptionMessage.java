package com.kacwol.githubRepoTask.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ExceptionMessage {

    private HttpStatus httpStatus;

    private String message;
}
