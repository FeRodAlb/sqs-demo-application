package com.ferodalb.sqs_demo_application.exception;

import com.ferodalb.sqs_demo_application.dto.response.DefaultResponse;
import com.ferodalb.sqs_demo_application.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DefaultResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        List<ErrorResponse> errorsList = errors.stream()
                .map(e -> new ErrorResponse(e.getField(), e.getDefaultMessage()))
                .toList();

        return new DefaultResponse(errorsList);
    }

}
