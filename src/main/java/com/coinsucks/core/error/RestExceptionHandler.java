package com.coinsucks.core.error;

import com.coinsucks.core.error.exception.ConflictException;
import com.coinsucks.core.error.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFound(NotFoundException ex) {
        log.error(ex.getMessage());
        ErrorResponseDto responseDto = new ErrorResponseDto(defaultIfEmpty(ex.getMessage(), "Not found"));
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponseDto> handleConflict(ConflictException ex) {
        log.error(ex.getMessage());
        ErrorResponseDto responseDto = new ErrorResponseDto(defaultIfEmpty(ex.getMessage(), "Conflict"));
        return new ResponseEntity<>(responseDto, HttpStatus.CONFLICT);
    }
}
