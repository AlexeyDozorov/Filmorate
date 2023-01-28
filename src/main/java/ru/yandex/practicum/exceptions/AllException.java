package ru.yandex.practicum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
@ControllerAdvice
public class AllException {
    @ExceptionHandler
    public ResponseEntity<Map<String,String>> filmException(FilmException e) {
        return new ResponseEntity<>(Map.of("Info",e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
