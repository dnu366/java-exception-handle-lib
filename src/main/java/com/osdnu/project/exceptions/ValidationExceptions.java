package com.osdnu.project.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.concurrent.atomic.AtomicInteger;

@ControllerAdvice
public class ValidationExceptions {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseException> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        AtomicInteger index = new AtomicInteger();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.append(fieldName).append(" ");
            errors.append(errorMessage);
            if (ex.getBindingResult().getAllErrors().size() > (index.get() + 1)) {
                errors.append(", ");

            }
            index.getAndIncrement();
        });

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseException responseException = new ResponseException();
        responseException.setErrorMessage(errors.toString());
        responseException.setErrorCode("0");
        return new ResponseEntity<>(responseException, headers, HttpStatus.BAD_REQUEST);
    }
}

